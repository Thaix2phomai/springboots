package vn.techmaster.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.bank.request.DepositRequest;
import vn.techmaster.bank.request.WithdrawRequest;
import vn.techmaster.bank.response.AccountInfo;
import vn.techmaster.bank.service.BankService;

@RestController
@RequestMapping("/api")
public class CommandController {
  @Autowired BankService bankService;

  @PostMapping("/deposit")
  public ResponseEntity<AccountInfo> deposit(@RequestBody DepositRequest depositRequest) {
    return ResponseEntity.ok(bankService.deposit(depositRequest));
  } 

  @PostMapping("/withdraw")
  public ResponseEntity<AccountInfo> withdraw(@RequestBody WithdrawRequest withdrawRequest) {
    return ResponseEntity.ok(bankService.withdraw(withdrawRequest));
  } 
}
