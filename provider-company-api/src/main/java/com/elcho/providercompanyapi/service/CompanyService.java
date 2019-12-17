package com.elcho.providercompanyapi.service;

import com.elcho.providercompanyapi.model.Company;
import java.util.List;

public interface CompanyService {

    void save(Company company);

    boolean isCompanyExists(String CompanyName);

    List<Company> getCompanyList();
}
