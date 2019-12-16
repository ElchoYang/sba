package com.elcho.providercompanyapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="TBL_STOCK_PRICE")
public class StockPriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String companyCode;

    @Column
    private String stockExchange;

    @Column
    private BigDecimal currentPrice; // stock price

    @Column
    private Date date;

    @Column
    private Time time;

}
