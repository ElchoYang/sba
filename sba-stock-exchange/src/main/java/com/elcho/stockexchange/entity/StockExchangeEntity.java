package com.elcho.stockexchange.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "TBL_STOCK_EXCHANGE")
public class StockExchangeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String stockExchange;

    @Column
    private String brief;

    @Column
    private String contactAddress;

    @Column
    private String  Remarks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "company_id", nullable = false)
    private CompanyEntity company;

}
