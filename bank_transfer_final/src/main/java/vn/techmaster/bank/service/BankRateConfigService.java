package vn.techmaster.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.bank.repository.BankRateConfigRepo;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class BankRateConfigService {

    @Autowired BankRateConfigRepo bankRateConfigRepo;

    public ConcurrentHashMap<Integer,Long> getAllRateConfig(){
        List<vn.techmaster.bank.model.BankRateConfig> allRate = bankRateConfigRepo.findAll();
        ConcurrentHashMap<Integer,Long> newMapRate = new ConcurrentHashMap<>();
        for(int i = 0 ; i <allRate.size();i++){
            newMapRate.put(allRate.get(i).getMonths(),allRate.get(i).getRate());
        }
        return newMapRate;
    }

    public Long findRateByMonth(int month){
        Long rateValue = getAllRateConfig().get(month);

        return rateValue;
    }

}
