package com.elcho.stockexchange.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
public class Company {

    private Long id;

    private String companyName;

    private BigDecimal turnOver; //换手率

    private String ceo;

    private String boardOfDirector; //董事会

    private List<StockExchange> stockExchangeList;

    private String sector;

    private String brief;

    private String code;

    private Date ipoDate;

}
