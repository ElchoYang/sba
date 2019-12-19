package com.elcho.providercompanyapi.service;

import com.elcho.providercompanyapi.model.StockPrice;

import java.util.List;

public interface StockPriceService {

    void save(List<StockPrice> stockPriceList);
}
