package vn.techmaster.bank.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Deposit extends Command{
  @ManyToOne(fetch = FetchType.LAZY)
  private Account account;

  private Long amount;

  public Deposit(User requester, Account account,  Long amount) {
    super(requester);
    this.account = account;
    this.amount = amount;
  }
}
