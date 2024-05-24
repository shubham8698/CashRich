package com.cashrich.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cashrich.model.CoinData;
import com.cashrich.repository.CoinDataRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CoinService {

    private static final String COINMARKETCAP_API_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=";
    private static final String API_KEY = "27ab17d1-215f-49e5-9ca4-afd48810c149";

    @Autowired
    private CoinDataRepository coinDataRepository;

    @Autowired
    private RestTemplate restTemplate;

    public CoinData getCoinData(String symbol, Long userId) {
        String url = COINMARKETCAP_API_URL + symbol;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        CoinData coinData = new CoinData(userId, symbol, response.getBody());
        coinData.setTimestamp(LocalDateTime.now());
        coinDataRepository.save(coinData);

        return coinData;
    }

    public List<CoinData> getCoinDataByUserId(Long userId) {
        return coinDataRepository.findByUserId(userId);
    }

    public List<CoinData> getCoinDataBySymbol(String symbol) {
        return coinDataRepository.findBySymbol(symbol);
    }
}
