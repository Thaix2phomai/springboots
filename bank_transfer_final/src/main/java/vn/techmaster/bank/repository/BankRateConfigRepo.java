package vn.techmaster.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.bank.model.BankRateConfig;
import vn.techmaster.bank.service.BankRateConfigService;

public interface BankRateConfigRepo extends JpaRepository<BankRateConfig, String> {
}
