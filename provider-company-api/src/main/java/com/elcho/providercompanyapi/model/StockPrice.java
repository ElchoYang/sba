package com.elcho.providercompanyapi.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class StockPrice {

    private long id;

    private String companyCode;

    private String stockExchange;

    private BigDecimal currentPrice; // stock price

    private Date date;

    private Time time;
}
