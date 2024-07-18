package by.svistunovvv.bankApi.service.impl;

import by.svistunovvv.bankApi.model.Rate;
import by.svistunovvv.bankApi.repository.RateRepository;
import by.svistunovvv.bankApi.service.RateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RateServiceImpl implements RateService {

    private RateRepository repository;
    @Override
    public Rate findRateByCurrencyAndDate(String currency, String date) {
        return repository.findRateByCurrencyAndDate(currency, date);
    }

    @Override
    public void saveAll(List<Rate> rates) {
        repository.saveAll(rates);
    }

    @Override
    public void save(Rate rate) {
        repository.save(rate);
    }
}
