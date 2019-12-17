package com.elcho.providercompanyapi.dao;


import com.elcho.providercompanyapi.dao.common.BaseRepository;
import com.elcho.providercompanyapi.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepositoty extends JpaRepository<CompanyEntity, Long> {

    Boolean existsByCompanyName(String companyName);

}
