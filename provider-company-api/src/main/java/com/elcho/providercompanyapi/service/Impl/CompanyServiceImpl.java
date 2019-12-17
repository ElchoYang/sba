package com.elcho.providercompanyapi.service.Impl;

import com.elcho.providercompanyapi.dao.CompanyRepositoty;
import com.elcho.providercompanyapi.entity.CompanyEntity;
import com.elcho.providercompanyapi.model.Company;
import com.elcho.providercompanyapi.service.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {


    @Autowired
    CompanyRepositoty companyReposity;

    @Override
    public void save(Company company) {
        CompanyEntity _companyEntity = new CompanyEntity();
        System.out.println(company);
        BeanUtils.copyProperties(company, _companyEntity, "id");

        if(_companyEntity!=null)
            companyReposity.save(_companyEntity);
    }

    @Override
    public boolean isCompanyExists(String CompanyName) {
        return companyReposity.existsByCompanyName(CompanyName);
    }

    @Override
    public List<Company> getCompanyList() {
        List<Company> _companyList = new ArrayList<>();

        for(CompanyEntity companyEntity : companyReposity.findAll()) {
            Company _company = new Company();
            BeanUtils.copyProperties(companyEntity, _company);
            _companyList.add(_company);

        }
        System.out.println(_companyList);
        return _companyList;
    }
}
