package vn.techmaster.bank.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Account {
  @Id
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Bank bank; //Mỗi account phải mở ở một ngân hàng
  
  @ManyToOne(fetch = FetchType.LAZY)
  private User user; //Mỗi account phải gắn vào một user

  private Long balance;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<SavingAccount> savingAccounts = new ArrayList<>();
}
