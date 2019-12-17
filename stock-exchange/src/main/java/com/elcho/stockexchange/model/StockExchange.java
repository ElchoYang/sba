package com.elcho.stockexchange.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockExchange {

    private long id;

    private String stockExchange;

    private BigDecimal currentPrice; // stock price

    private String brief;

    private String contactAddress;

    private String  Remarks;

}
