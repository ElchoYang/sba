package com.elcho.stockexchange.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

@Data
public class StockPrice {

    private long id;

    private String companyCode;

    private String stockExchange;

    private BigDecimal currentPrice; // stock price

    private Date date;

    private Time time;
}
