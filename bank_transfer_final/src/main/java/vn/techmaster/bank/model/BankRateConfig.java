package vn.techmaster.bank.model;

import jdk.jfr.Enabled;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class BankRateConfig {
    @Id
    private String id;

    private int months;

    private Long rate;

}
