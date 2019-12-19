package com.elcho.providercompanyapi.service.Impl;

import com.elcho.providercompanyapi.dao.StockPriceRepository;
import com.elcho.providercompanyapi.entity.StockPriceEntity;
import com.elcho.providercompanyapi.model.StockPrice;
import com.elcho.providercompanyapi.service.StockPriceService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockPriceImpl implements StockPriceService {

    private static final Logger log = LoggerFactory.getLogger(StockPriceImpl.class);

    @Autowired
    StockPriceRepository stockPriceRepository;

    @Override
    public void save(List<StockPrice> StockPriceList) {
        List<StockPriceEntity> stockPriceEntities = new ArrayList<>();

        for(StockPrice sp: StockPriceList){
            StockPriceEntity _spEntity = new StockPriceEntity();

            BeanUtils.copyProperties(sp, _spEntity, "id");

            stockPriceEntities.add(_spEntity);
        }

        stockPriceRepository.saveAll(stockPriceEntities);
    }
}
