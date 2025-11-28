package com.rohit.stock_tracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {


    @Value("${indian.stock.api.key}")
    private String apiKey;

    @Bean
    public WebClient webClient(@Value("${indian.stock.api.base.url}") String baseUrl){
        return WebClient.builder().baseUrl(baseUrl).defaultHeader("X-Api-Key", apiKey).exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(2 * 1024 * 1024)) // 2MB
                .build()
        ).build();
    }
}
