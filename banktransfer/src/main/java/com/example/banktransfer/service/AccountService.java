package com.example.banktransfer.service;

import com.example.banktransfer.exception.DepositException;
import com.example.banktransfer.exception.TransferException;
import com.example.banktransfer.exception.WithDrawException;
import com.example.banktransfer.model.Account;
import com.example.banktransfer.repository.AccountRepo;
import com.example.banktransfer.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private UserRepo userRepo;

    @Autowired

    private AccountRepo accountRepo;


    public void transferAction (String bankingAccount, String receivingAccount, Long amount) {
        Optional<Account> findBankingAccount = accountRepo.findById(bankingAccount);

        Account sendAccount = new Account();


        if (findBankingAccount.isEmpty()){
            throw new TransferException("Can't found Account with id" + bankingAccount);
        }else{
            sendAccount = findBankingAccount.get();
        }

        Optional<Account> findReceivingAccount = accountRepo.findById(receivingAccount);

        Account receiveAccount= new Account();

        if (findReceivingAccount.isEmpty()){
            throw new TransferException("Can't found Account with id" + receivingAccount);
        }else{
            receiveAccount = findReceivingAccount.get();
        }


        if (sendAccount.getBalance() < amount) {
            throw new TransferException("Your money is not enough to make a transaction");
        } if (amount < 0){

            throw new TransferException("The amount of money can't not be under 0");
        }if (amount > 10000000){
            throw new TransferException("the amount of money can't not reach out of 10000000");
        }

        sendAccount.setBalance(sendAccount.getBalance() - amount);



        receiveAccount.setBalance(receiveAccount.getBalance() + amount);

        accountRepo.save(sendAccount);

        accountRepo.save(receiveAccount);

    }

    public void depositAction(String depositAccountId, Long amount){
            Optional<Account> findDepositAccount = accountRepo.findById(depositAccountId);
            Account depositAccount = new Account();
            if (findDepositAccount.isPresent()){
                depositAccount = findDepositAccount.get();
            }else {
                throw new DepositException("Account Not Available");
            }

            depositAccount.setBalance(depositAccount.getBalance() + amount);

            accountRepo.save(depositAccount);

    }

    public void withdrawAction(String withdrawAccountId, Long amount){
        Optional<Account> findDepositAccount = accountRepo.findById(withdrawAccountId);
        Account withdrawAccount = new Account();
        if (findDepositAccount.isPresent()){
            withdrawAccount = findDepositAccount.get();
        }else {
            throw new WithDrawException("Account Not Available");
        }

        withdrawAccount.setBalance(withdrawAccount.getBalance() - amount);

        accountRepo.save(withdrawAccount);

    }


}
