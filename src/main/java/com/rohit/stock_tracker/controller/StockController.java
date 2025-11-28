package com.rohit.stock_tracker.controller;

import com.rohit.stock_tracker.dto.DailyStockResponse;
import com.rohit.stock_tracker.dto.FavoriteStockRequest;
import com.rohit.stock_tracker.dto.StockOverviewResponse;
import com.rohit.stock_tracker.dto.StockResponse;
import com.rohit.stock_tracker.entity.FavoriteStock;
import com.rohit.stock_tracker.service.StockService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {
    private final StockService stockService;

    @Autowired
    public StockController(final StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{stockSymbol}")
    public StockResponse getStock(@PathVariable String stockSymbol) {
        return stockService.getStockForSymbol(stockSymbol.toUpperCase());
    }

    @GetMapping("/{stockSymbol}/overview")
    public StockOverviewResponse getStockOverview(@PathVariable String stockSymbol) {
        return stockService.getStockOverviewForSymbol(stockSymbol.toUpperCase());
    }

    @GetMapping("/{stockSymbol}/history")
    public List<DailyStockResponse> getStockHistory(@PathVariable String stockSymbol, @RequestParam(defaultValue = "30") int days) {
        return stockService.getStockHistoryForSymbol(stockSymbol.toUpperCase(), days);
    }

    @PostMapping("/favourites")
    public ResponseEntity<@NonNull FavoriteStock> saveFavoriteStock(@RequestBody FavoriteStockRequest request) {
        final FavoriteStock saved = stockService.addFavorite(request.getSymbol());
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/favorites")
    public List<StockResponse> getFavoritesWithPrices() {
        return stockService.getFavoritesWithLivePrices();
    }
}
