package by.svistunovvv.bankApi.helper.converter;

import by.svistunovvv.bankApi.helper.NbrbResponse;
import by.svistunovvv.bankApi.model.Rate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class RateConverter {
    private ObjectMapper objectMapper;

    public List<Rate> convertArray(String data) {
        List<NbrbResponse> nbrbResponses;
        try {
            nbrbResponses = Arrays.asList(objectMapper.readValue(data.toLowerCase(), NbrbResponse[].class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (nbrbResponses.isEmpty()) {
            return null;
        }
        return nbrbResponses.stream().map(this::buildRateFromNbrbResponse).toList();
    }

    public Rate convertRate(String data) {
        NbrbResponse nbrbResponse;
        try {
            nbrbResponse = objectMapper.readValue(data.toLowerCase(), NbrbResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (nbrbResponse == null) {
            return null;
        }
        return buildRateFromNbrbResponse(nbrbResponse);
    }

    private Rate buildRateFromNbrbResponse(NbrbResponse nbrbResponse) {
        return Rate.builder()
                .officialRate(nbrbResponse.getCur_officialrate())
                .scale(nbrbResponse.getCur_scale())
                .date(formatNbrbDate(nbrbResponse.getDate()))
                .currency(nbrbResponse.getCur_abbreviation())
                .name(nbrbResponse.getCur_name())
                .build();
    }

    private String formatNbrbDate(String dateTimeStr) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd't'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, inputFormatter);
        LocalDate date = dateTime.toLocalDate();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(outputFormatter);
    }
}
