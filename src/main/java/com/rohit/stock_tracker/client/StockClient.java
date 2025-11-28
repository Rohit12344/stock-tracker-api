package com.rohit.stock_tracker.client;

import com.rohit.stock_tracker.dto.IndianStockAPIResponse;
import com.rohit.stock_tracker.dto.StockHistoryResponse;
import com.rohit.stock_tracker.dto.StockOverviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class StockClient {

    private final WebClient webClient;

    public IndianStockAPIResponse getStockQuote(String symbol) {
//        String raw = webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/stock")
//                        .queryParam("name", symbol)
//                        .build())
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//
//        System.out.println(symbol);
//        System.out.println("RAW API RESPONSE:");
//        System.out.println(raw);

        // Now map it
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/stock")
                        .queryParam("name", symbol)
                        .build())
                .retrieve()
                .bodyToMono(IndianStockAPIResponse.class)
                .block();
    }

    public StockOverviewResponse getStockOverview(String symbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/stock")
                        .queryParam("name", symbol)
                        .build())
                .retrieve()
                .bodyToMono(StockOverviewResponse.class)
                .block();
    }

    public StockHistoryResponse getStockHistory(String symbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/historical_data")
                        .queryParam( "stock_name", symbol)
                        .queryParam("period", "1m")
                        .queryParam("filter", "default")
                        .build())
                .retrieve()
                .bodyToMono(StockHistoryResponse.class)
                .block();
    }

}
