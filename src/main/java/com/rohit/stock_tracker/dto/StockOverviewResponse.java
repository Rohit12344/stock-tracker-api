package com.rohit.stock_tracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record StockOverviewResponse(
        @JsonProperty("companyProfile")
        CompanyProfile companyProfile,

        @JsonProperty("stockDetailsReusableData")
        StockDetailsReusableData stockDetailsReusableData,

        @JsonProperty("companyName")
        String companyName) {
    public record CompanyProfile(
            @JsonProperty("exchangeCodeNse")
            String symbol,
            @JsonProperty("companyDescription")
            String companyName,
            @JsonProperty("mgIndustry")
            String industry
    ) {
    }

    public record StockDetailsReusableData(
            @JsonProperty("marketCap")
            String marketCap,

            @JsonProperty("pPerEBasicExcludingExtraordinaryItemsTTM")
            String peRatio,

            @JsonProperty("currentDividendYieldCommonStockPrimaryIssueLTM")
            String dividendYield
    ) {
    }
}
