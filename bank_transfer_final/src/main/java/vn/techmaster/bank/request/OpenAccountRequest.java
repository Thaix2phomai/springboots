package vn.techmaster.bank.request;

import vn.techmaster.bank.model.AccountType;

public record OpenAccountRequest (String accountName, String hashPassword, Long balance, AccountType accountType){

}

