package com.cashrich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.cashrich.model.CoinData;
import com.cashrich.service.CoinService;
import com.cashrich.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/coin")
public class CoinController {

    @Autowired
    private CoinService coinService;

    @Autowired
    private UserService userService;

    @GetMapping("/data")
    public ResponseEntity<CoinData> getCoinData(Authentication authentication, @RequestParam String symbol) {
        Long userId = getUserIdFromAuthentication(authentication);
        CoinData coinData = coinService.getCoinData(symbol, userId);
        return ResponseEntity.ok(coinData);
    }

    @GetMapping("/data/user")
    public ResponseEntity<List<CoinData>> getCoinDataByUserId(Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        List<CoinData> coinDataList = coinService.getCoinDataByUserId(userId);
        return ResponseEntity.ok(coinDataList);
    }

    @GetMapping("/data/symbol")
    public ResponseEntity<List<CoinData>> getCoinDataBySymbol(@RequestParam String symbol) {
        List<CoinData> coinDataList = coinService.getCoinDataBySymbol(symbol);
        return ResponseEntity.ok(coinDataList);
    }

    private Long getUserIdFromAuthentication(Authentication authentication) {
        return userService.getUserIdFromAuthentication(authentication);
    }
}
