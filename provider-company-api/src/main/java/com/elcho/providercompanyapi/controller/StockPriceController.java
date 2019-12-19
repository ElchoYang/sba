package com.elcho.providercompanyapi.controller;


import com.elcho.providercompanyapi.model.StockPrice;
import com.elcho.providercompanyapi.service.StockPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class StockPriceController {

    private static final Logger log = LoggerFactory.getLogger(StockPriceController.class);

    @Autowired
    StockPriceService stockPriceService;

    @RequestMapping(value = "/newPriceList", method = RequestMethod.POST)
    public ResponseEntity<Integer> AddStockPrice(@RequestBody ArrayList<StockPrice> stockPriceServiceList) {
        Integer status = -1;
        log.info("=============newPriceList Being ==============");
        log.info(String.valueOf(stockPriceServiceList.get(0)));

        stockPriceService.save(stockPriceServiceList);
        status = 0;
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }
}
