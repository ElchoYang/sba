package com.elcho.providercompanyapi.controller;

import com.elcho.providercompanyapi.model.Company;
import com.elcho.providercompanyapi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class CompanyController {
    private static final Logger log = Logger.getLogger(String.valueOf(CompanyController.class));

    @Autowired
    CompanyService companyService;

    // Create company
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Integer> createUser(@RequestBody Company company, UriComponentsBuilder ucBuilder) {
        Integer status = -1;

        log.info("consumer端传输过来的company是：" + company);
        //判断用户名是否已经存在
        if (companyService.isCompanyExists(company.getCompanyName())) {
            //
            log.info("company：%s已经存在了\n" + company.getCompanyName());
            return new ResponseEntity<>(status, HttpStatus.CONFLICT);
        }
        //调用业务方法
        companyService.save(company);
        status = 0;

        log.info("Create Company successful!");
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    // Create company
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Company>> getAllCompany() {
        return new ResponseEntity<>(companyService.getCompanyList(), HttpStatus.OK);
    }

}
