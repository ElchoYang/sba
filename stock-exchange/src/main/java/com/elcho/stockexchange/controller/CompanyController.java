package com.elcho.stockexchange.controller;

import com.elcho.stockexchange.model.Company;
import com.elcho.stockexchange.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping(value = "/stockexchange/company")
public class CompanyController {
    private static final Logger log = Logger.getLogger(String.valueOf(UserController.class));

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/new", method = {RequestMethod.POST})
    public ResponseEntity<Integer> create(@RequestBody Company company, UriComponentsBuilder ucBuilder){
        log.info("客户端传输过来的company ："+company);
        ResponseEntity<Integer> entity =  restTemplate.postForEntity("http://PROVIDER-COMPANY-API/new",company, Integer.class);
        HttpStatus statusCode  = entity.getStatusCode();
        log.info("Status Code: " + statusCode);

        Integer status = entity.getBody();

        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public ResponseEntity<List<Company>> allCompany(){

        ResponseEntity<List> entity =  restTemplate.getForEntity("http://PROVIDER-COMPANY-API/all", List.class);
        HttpStatus statusCode  = entity.getStatusCode();
        log.info("Status Code: " + statusCode);
        List<Company> companyList = entity.getBody();

        if(companyList == null || companyList.size()<=0) {
            companyList = new ArrayList<Company>();
        }

        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

}
