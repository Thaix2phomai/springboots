package vn.techmaster.bank.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavingAccount {
    @Id
    private String id;
    private Long startBalance;
    private Long endBalance;
    private int months;
    private Long rate;
    @Enumerated(EnumType.STRING)
    private SavingType savingType;
    private LocalDateTime openAt;
    private LocalDateTime updateAt;
    private LocalDateTime closeAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account; //Mỗi accountsaver phải gắn vào một account

}
