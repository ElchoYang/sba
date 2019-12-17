package com.elcho.providercompanyapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name ="TBL_COMPANY")
public class CompanyEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String companyName;

    @Column
    private BigDecimal turnOver; //换手率

    @Column
    private String ceo;

    @Column
    private String boardOfDirector; //董事会

    @Column
    private String sector;

    @Column
    private String brief;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<StockExchangeEntity> stockExchangeList;
}
