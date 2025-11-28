package com.rohit.stock_tracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record StockHistoryResponse(
        @JsonProperty("datasets")
        List<MetricHistory> metricHistory
        ) {

}
