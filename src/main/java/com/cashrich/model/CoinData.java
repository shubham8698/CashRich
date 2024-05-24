package com.cashrich.model;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "coin_data")
public class CoinData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String symbol;

    @Lob
    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public CoinData() {
        this.timestamp = LocalDateTime.now();
    }

    public CoinData(Long userId, String symbol, String data) {
        this.userId = userId;
        this.symbol = symbol;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

}
