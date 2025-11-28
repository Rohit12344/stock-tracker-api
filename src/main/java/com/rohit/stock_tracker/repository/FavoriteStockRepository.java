package com.rohit.stock_tracker.repository;

import com.rohit.stock_tracker.entity.FavoriteStock;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteStockRepository extends JpaRepository<@NonNull FavoriteStock, @NonNull Long> {
    boolean existsBySymbol(String symbol);

}
