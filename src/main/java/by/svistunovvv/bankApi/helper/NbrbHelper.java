package by.svistunovvv.bankApi.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class NbrbHelper<A> {
    private final String NBRB_API = "https://api.nbrb.by/exrates/rates/";
    private final int PERIODICITY_PARAM = 0;
    private final int PARAMMODE_PARAM = 2;

    public String getData(String date, String currencyCode) {
        String url = currencyCode.isEmpty() ? NBRB_API : NBRB_API + currencyCode + "/" ;
        WebClient client = WebClient.builder()
                .baseUrl(url)
                .build();
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("periodicity", PERIODICITY_PARAM)
                        .queryParam("parammode", PARAMMODE_PARAM)
                        .queryParam("ondate", date)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> Mono.empty())
                .block();
    }
}
