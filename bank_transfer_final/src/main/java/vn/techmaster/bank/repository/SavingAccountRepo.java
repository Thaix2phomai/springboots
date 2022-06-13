package vn.techmaster.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.bank.model.SavingAccount;

@Repository
public interface SavingAccountRepo extends JpaRepository<SavingAccount, String> {

}
