package com.elcho.stockexchange.model;

import lombok.Data;

import java.util.Date;

@Data
public class PriceUploadSummary {
    private String Company;

    private String stockExchange;

    private Integer RecordNumber;

    private Date formDate;

    private Date toDate;
}
