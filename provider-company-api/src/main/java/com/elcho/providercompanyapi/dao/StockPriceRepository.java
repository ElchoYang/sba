package com.elcho.providercompanyapi.dao;

import com.elcho.providercompanyapi.dao.common.BaseRepository;
import com.elcho.providercompanyapi.entity.StockPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPriceRepository extends JpaRepository<StockPriceEntity, Long> {
}
