package by.svistunovvv.bankApi.service;

import by.svistunovvv.bankApi.model.Rate;

import java.util.Date;
import java.util.List;

public interface RateService {
    Rate findRateByCurrencyAndDate(String currency, String date);
    void saveAll(List<Rate> rates);
    void save(Rate rate);
}
