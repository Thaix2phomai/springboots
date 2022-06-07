package com.example.banktransfer.controller;


import com.example.banktransfer.repository.UserRepo;
import com.example.banktransfer.model.Account;
import com.example.banktransfer.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/user-by-id")
    @Operation(summary = "Find User By ID")
    public ResponseEntity<?> findUserById(@Parameter(description = "user_id")@RequestParam String id) {
        Optional<User> getUserById = userRepo.findById(id);
        return ResponseEntity.ok(getUserById);
    }

    @GetMapping("/account-by-userId")
    @Operation(summary = "Find Account By User ID")
    public ResponseEntity<?> findAcountByUserId (@Parameter(description = "user_id")@RequestParam String id){
        Optional<User> getUserId = userRepo.findById(id);
        List<Account> accountByUserId = getUserId.get().getAccounts();
        return ResponseEntity.ok(accountByUserId);
    }


}
