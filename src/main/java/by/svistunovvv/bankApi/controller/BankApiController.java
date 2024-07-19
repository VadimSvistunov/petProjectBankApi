package by.svistunovvv.bankApi.controller;

import by.svistunovvv.bankApi.helper.NbrbHelper;
import by.svistunovvv.bankApi.helper.converter.RateConverter;
import by.svistunovvv.bankApi.model.Rate;
import by.svistunovvv.bankApi.service.RateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class BankApiController {
    private final RateService rateService;
    private final RateConverter rateConverter;
    private final NbrbHelper nbrbHelper;

    @GetMapping("/preload")
    public String preload(@RequestParam String date) {
        String response = nbrbHelper.getData(date, "");
        if (response == null) {
            return "Something went wrong";
        }
        List<Rate> rates = rateConverter.convertArray(response);
        rateService.saveAll(rates);
        return "Success preload rates";
    }

    @GetMapping("/rate")
    public String loadRate(
            @RequestParam String date,
            @RequestParam String currencyCode
    ) {
        Rate rate = rateService.findRateByCurrencyAndDate(currencyCode.toLowerCase(), date);
        if (rate == null) {
            String response = nbrbHelper.getData(date, currencyCode);
            if (response == null) {
                return "Something went wrong";
            }
            rate = rateConverter.convertRate(response);
            rateService.save(rate);
        }
        return rate.getDate() + " за " + rate.getScale() + " " + rate.getName() + " надо было отдать " + rate.getOfficialRate() + " рублей";
    }
}
