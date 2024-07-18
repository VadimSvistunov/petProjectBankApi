package by.svistunovvv.bankApi.repository;

import by.svistunovvv.bankApi.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface RateRepository extends JpaRepository<Rate, Long> {
    Rate findRateByCurrencyAndDate(String currency, String date);
}
