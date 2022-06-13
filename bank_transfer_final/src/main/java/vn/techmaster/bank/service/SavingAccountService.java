package vn.techmaster.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.bank.exception.CommandException;
import vn.techmaster.bank.exception.RecordNotFoundException;
import vn.techmaster.bank.model.Account;
import vn.techmaster.bank.model.SavingAccount;
import vn.techmaster.bank.model.SavingType;
import vn.techmaster.bank.model.User;
import vn.techmaster.bank.repository.AccountRepo;
import vn.techmaster.bank.repository.SavingAccountRepo;
import vn.techmaster.bank.repository.UserRepo;
import vn.techmaster.bank.request.SavingAccountRequest;
import vn.techmaster.bank.request.WithDrawSavingRequest;
import vn.techmaster.bank.response.SavingAccountInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SavingAccountService {
    @Autowired
    private UserRepo userRepo;
    @Autowired private AccountRepo accountRepo;
    @Autowired private SavingAccountRepo savingAccountRepo;
    @Autowired private BankRateConfigService bankRateConfigService;

    public SavingAccountInfo openAccount(SavingAccountRequest savingAccountRequest){
        User user = userRepo.findById(savingAccountRequest.userID())
                .orElseThrow(() -> new RecordNotFoundException("users", savingAccountRequest.userID()));

        Account account = accountRepo.findById(savingAccountRequest.accountID())
                .orElseThrow(() -> new RecordNotFoundException("account", savingAccountRequest.accountID()));

        if(savingAccountRequest.amount() > account.getBalance()){
            throw new CommandException("Not Enough Balance To Transfer");
        }

        if(bankRateConfigService.findRateByMonth(savingAccountRequest.months()) == null){
            throw new CommandException("This month is not valid");
        }

        account.setBalance(account.getBalance()- savingAccountRequest.amount());
        SavingAccount newSavingAccount;
        if(savingAccountRequest.savingType().toString().equals("FINAL")){
            newSavingAccount = SavingAccount.builder()
                    .id(UUID.randomUUID().toString())
                    .account(account)
                    .startBalance(savingAccountRequest.amount())
                    .endBalance(savingAccountRequest.amount())
                    .months(savingAccountRequest.months())
                    .rate(bankRateConfigService.findRateByMonth(savingAccountRequest.months()))
                    .savingType(savingAccountRequest.savingType())
                    .openAt(LocalDateTime.now())
                    .updateAt(null)
                    .closeAt(LocalDateTime.now().plusMonths(savingAccountRequest.months())).build();
        }
        newSavingAccount = SavingAccount.builder()
                .id(UUID.randomUUID().toString())
                .account(account)
                .startBalance(savingAccountRequest.amount())
                .endBalance(savingAccountRequest.amount())
                .months(savingAccountRequest.months())
                .rate(bankRateConfigService.findRateByMonth(savingAccountRequest.months()))
                .savingType(savingAccountRequest.savingType())
                .openAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now().plusMonths(1))
                .closeAt(LocalDateTime.now().plusMonths(savingAccountRequest.months())).build();
        savingAccountRepo.save(newSavingAccount);

        return new SavingAccountInfo(newSavingAccount.getId(),
                newSavingAccount.getStartBalance(),
                newSavingAccount.getMonths(),
                newSavingAccount.getRate(),
                newSavingAccount.getOpenAt(), newSavingAccount.getCloseAt());
    }

    public String withDrawSaveAccount(WithDrawSavingRequest withDrawSavingRequest){
        User user = userRepo.findById(withDrawSavingRequest.userID())
                .orElseThrow(() -> new RecordNotFoundException("users", withDrawSavingRequest.userID()));

        Account account = accountRepo.findById(withDrawSavingRequest.accountID())
                .orElseThrow(() -> new RecordNotFoundException("account", withDrawSavingRequest.accountID()));

        SavingAccount savingAccount = savingAccountRepo.findById(withDrawSavingRequest.accountSaverID())
                .orElseThrow(() -> new RecordNotFoundException("accountsaver", withDrawSavingRequest.accountSaverID()));
        if(savingAccount.getSavingType().equals(SavingType.ENDOFTERM)){
            Long bonusBalance;
            if(savingAccount.getMonths() == 0){
                bonusBalance= savingAccount.getStartBalance()*((savingAccount.getRate()/100)/12);
            } else {
                bonusBalance = savingAccount.getStartBalance()*savingAccount.getMonths()*((savingAccount.getRate()/100)/12);
            }
            if(savingAccount.getMonths() != 0 && savingAccount.getCloseAt().compareTo(LocalDateTime.now()) > 0){
                throw new CommandException("Chưa đến hạn rút lãi");
            }

            account.setBalance(account.getBalance()+bonusBalance);
            accountRepo.save(account);
            savingAccount.setStartBalance(0L);
            savingAccountRepo.deleteById(savingAccount.getId());

            return "Tài khoản "+ account.getId() + " nhận thành công khoản lãi: "+ bonusBalance;
        }
        final Long currentBalance = savingAccount.getStartBalance();
        double bonusBalancePerMonth = currentBalance*(((savingAccount.getRate()*0.8)/100)/12);
        if(savingAccount.getUpdateAt().compareTo(savingAccount.getCloseAt()) < 0){
            if(savingAccount.getUpdateAt().compareTo(LocalDateTime.now()) > 0){
                throw new CommandException("Chưa đến hạn rút lãi");
            } else {
                savingAccount.setEndBalance((long) (savingAccount.getEndBalance()+bonusBalancePerMonth));
                savingAccount.setUpdateAt(savingAccount.getUpdateAt().plusMonths(1));
                savingAccountRepo.save(savingAccount);
                return "Tài khoản tiết kiệm "+ savingAccount.getId() + " nhận thành công khoản lãi hàng tháng: "+ bonusBalancePerMonth;
            }

        } else {
            account.setBalance((long) (account.getBalance()+savingAccount.getEndBalance()+bonusBalancePerMonth));
            accountRepo.save(account);
            savingAccountRepo.deleteById(savingAccount.getId());
            return "Tài khoản "+ account.getId() + " nhận thành công khoản gốc kèm lãi: "+ (savingAccount.getEndBalance()+bonusBalancePerMonth) + " với số tiền lãi: "+
                    currentBalance*savingAccount.getMonths()*(((savingAccount.getRate()*0.8)/100)/12);
        }


    }

    public SavingAccount findAccSavebyAccID(String accountID){
        List<SavingAccount> listAccSave = savingAccountRepo.findAll();
        SavingAccount finalAccSave = new SavingAccount();
        for (int i = 0; i < listAccSave.size(); i++) {
            if(listAccSave.get(i).getAccount().getId().equalsIgnoreCase(accountID)){
                finalAccSave = listAccSave.get(i);
            }
            else {
                throw new CommandException("Account is not found");
            }
        }
        return finalAccSave;
    }
}
