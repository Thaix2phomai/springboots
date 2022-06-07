package com.example.banktransfer.controller;

import com.example.banktransfer.repository.AccountRepo;
import com.example.banktransfer.model.Account;
import com.example.banktransfer.request.DepositAndWithDrawRequest;
import com.example.banktransfer.request.TransferRequest;
import com.example.banktransfer.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepo accountRepo;


    @Autowired
    private AccountService accountService;

    @GetMapping("/account-by-id")
    @Operation(summary = "Show Account By ID")
    public ResponseEntity<?> findAccountById(@RequestParam String id) {
        Optional<Account> getAccountById = accountRepo.findById(id);
        return ResponseEntity.ok(getAccountById);
    }

    @GetMapping("/transfer")
    @Operation(summary = "Transfer Action")
    public ResponseEntity<?> transaction (@Parameter(description = "transfer action")@RequestBody TransferRequest transferRequest){
        accountService.transferAction(transferRequest.sendId(), transferRequest.reiceiveId(), transferRequest.amount());
        return ResponseEntity.ok("transaction completed");

    }

    @GetMapping("/deposit")
    @Operation(summary = "Deposit Action ")
    public ResponseEntity<?> depositAction (@Parameter(description = "deposit action")@RequestBody DepositAndWithDrawRequest depositAndWithDrawRequest){
        accountService.depositAction(depositAndWithDrawRequest.accId(), depositAndWithDrawRequest.amount());
        return ResponseEntity.ok(("Action Completed"));

    }

    @GetMapping("/withdraw")
    @Operation(summary = "Withdraw Action")
    public ResponseEntity<?> withdrawAction (@Parameter(description = "withdraw action")@RequestBody DepositAndWithDrawRequest depositAndWithDrawRequest){
        accountService.withdrawAction(depositAndWithDrawRequest.accId(), depositAndWithDrawRequest.amount());
        return ResponseEntity.ok(("Action Completed"));
    }


}
