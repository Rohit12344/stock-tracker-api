package com.rohit.stock_tracker.service;

import com.rohit.stock_tracker.client.StockClient;
import com.rohit.stock_tracker.dto.*;
import com.rohit.stock_tracker.entity.FavoriteStock;
import com.rohit.stock_tracker.exception.FavoriteAlreadyExistsException;
import com.rohit.stock_tracker.repository.FavoriteStockRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    private final StockClient stockClient;

    private final FavoriteStockRepository favoriteStockRepository;

    public StockService(final StockClient stockClient,final FavoriteStockRepository favoriteStockRepository) {
        this.stockClient = stockClient;
        this.favoriteStockRepository =favoriteStockRepository;
    }

    public StockResponse getStockForSymbol(final String stockSymbol) {
        IndianStockAPIResponse response = stockClient.getStockQuote(stockSymbol);

        System.out.println(stockSymbol);

        return StockResponse.builder().price(response.stockDetailsReusableData().price())
                .lastTradingDate(response.stockDetailsReusableData().lastTradingDate())
                .symbol(response.companyProfile().symbol())
                .build();
    }

    public StockOverviewResponse getStockOverviewForSymbol(final String stockSymbol) {
        return stockClient.getStockOverview(stockSymbol);
    }

    public List<DailyStockResponse> getStockHistoryForSymbol(String symbol, int days) {
        StockHistoryResponse response = stockClient.getStockHistory(symbol);

        return new ArrayList<>(response.metricHistory().stream()
                .filter(m -> "Price".equalsIgnoreCase(m.metricName()))
                .flatMap(m -> m.historyList().stream())
                .map(values -> new DailyStockResponse(values.get(0).toString(), values.get(1).toString()))
                .sorted(Comparator.reverseOrder())
                .limit(days)
                .toList()

        );

    }

    @Transactional
    public FavoriteStock addFavorite(final String symbol) {
        if(favoriteStockRepository.existsBySymbol(symbol)){
            throw new FavoriteAlreadyExistsException(symbol);
        }

        FavoriteStock favorite = FavoriteStock.builder().symbol(symbol).build();

        return favoriteStockRepository.save(favorite);
    }

    public List<StockResponse> getFavoritesWithLivePrices() {
        List<FavoriteStock> favorites = favoriteStockRepository.findAll();

        return favorites.stream()
                .map(fav -> getStockForSymbol(fav.getSymbol()))
                .collect(Collectors.toList());
    }
}
