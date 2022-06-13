package vn.techmaster.bank.response;

import java.time.LocalDateTime;

public record SavingAccountInfo (String accountsaverId, Long startBalance, int months, Long rate, LocalDateTime openAt, LocalDateTime closeAt) {
}
