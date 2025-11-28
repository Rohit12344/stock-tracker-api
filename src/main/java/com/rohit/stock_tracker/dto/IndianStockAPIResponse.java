package com.rohit.stock_tracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IndianStockAPIResponse(
        @JsonProperty("companyProfile")
        CompanyProfile companyProfile,

        @JsonProperty("stockDetailsReusableData")
        StockDetailsReusableData stockDetailsReusableData
) {

    public record StockDetailsReusableData(
            @JsonProperty("price")
            String price,

            @JsonProperty("date")
            String lastTradingDate
    ) {
    }

    public record CompanyProfile(@JsonProperty("exchangeCodeNse")
                                 String symbol) {

    }
}

