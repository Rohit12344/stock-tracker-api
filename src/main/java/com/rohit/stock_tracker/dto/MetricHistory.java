package com.rohit.stock_tracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record MetricHistory (
        @JsonProperty("metric")
        String metricName,

        @JsonProperty("values")
        List<List<Object>> historyList
) {
}
