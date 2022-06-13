package vn.techmaster.bank.request;

import vn.techmaster.bank.model.SavingType;

public record SavingAccountRequest(String userID, String accountID, int months, Long amount, SavingType savingType) {}

