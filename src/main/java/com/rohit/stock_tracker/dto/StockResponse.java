package com.rohit.stock_tracker.dto;

import lombok.Builder;

@Builder
public record StockResponse(String price, String lastTradingDate, String symbol) {
}
