package com.rohit.stock_tracker.dto;

import java.time.LocalDate;

public record DailyStockResponse (
        String date,
        String price
) implements Comparable<DailyStockResponse> {
    @Override
    public int compareTo(DailyStockResponse o) {
        return LocalDate.parse(this.date).compareTo(LocalDate.parse(o.date));
    }
}
