package vn.techmaster.bank.request;

public record WithdrawRequest(String userId, String accountId, Long amount) {

}