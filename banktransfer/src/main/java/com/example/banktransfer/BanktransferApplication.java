package com.example.banktransfer;

import com.example.banktransfer.repository.AccountRepo;
import com.example.banktransfer.repository.UserRepo;
import com.example.banktransfer.model.Account;
import com.example.banktransfer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class BanktransferApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BanktransferApplication.class, args);
    }

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {





        Account account = new Account("1", "Bobaccount1", "12345678", 10000L);

        Account account1 = new Account("2", "Bobaccount2", "12345678", 10000L);

        Account account2 = new Account("3", "Aliceaccount1", "12345678", 10000L);

        Account account3 = new Account("4", "Aliceaccount2", "12345678", 10000L);


        Account account4 = new Account("5", "Sarahccount", "12345678", 10000L);
        Account account5 = new Account("6", "Tomaccount", "12345678", 10000L);

        List<Account> arr = new ArrayList<>();
        arr.add(account);
        arr.add(account1);

        List<Account> arr1 = new ArrayList<>();
        arr1.add(account2);
        arr1.add(account3);

        List<Account> arr2 = new ArrayList<>();
        arr2.add(account4);

        List<Account> arr3 = new ArrayList<>();
        arr2.add(account5);

        accountRepo.save(account);
        accountRepo.save(account1);

        accountRepo.save(account2);

        accountRepo.save(account3);

        accountRepo.save(account4);

        accountRepo.save(account5);

        User user = new User("7", "Bob", "New York", "Bob123@gmail.com", arr);
        User user1 = new User("8", "Alice", "Dubai", "Alice123@gmail.com", arr1);

        User user2 = new User("9", "Sarah", "France", "Sarah123@gmail.com", arr2);

        User user3 = new User("10", "Tom", "Japan", "Tom123@gmail.com", arr3);
        userRepo.save(user);
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);







    }
}

