package com.elcho.stockexchange.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "TBL_IPO")
public class IpoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String companyName;

    @Column
    private String stockExchange;

    @Column
    private BigDecimal pricePerShare;

    @Column
    private Integer totalShare;

    @Column
    private Date openDate;

    @Column
    private String remarks;
}
