package com.example.banktransfer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class Account {
    @Id
    private String id;

    private String accountName;

    private String hashPassword;

    private Long balance;


    public Account(String id, String accountName, String hashPassword, Long balance) {
        this.id = id;
        this.accountName = accountName;
        this.hashPassword = hashPassword;
        this.balance = balance;

    }
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


}
