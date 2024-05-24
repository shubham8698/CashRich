package com.cashrich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cashrich.model.CoinData;

import java.util.List;

@Repository
public interface CoinDataRepository extends JpaRepository<CoinData, Long> {
    List<CoinData> findByUserId(Long userId);
    List<CoinData> findBySymbol(String symbol);
}
