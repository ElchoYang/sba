package com.elcho.stockexchange;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class SbaStockExchangeInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        //
        System.out.println("--- 基于web 方式启动SpringBoot应用....");
        //
        return builder.sources(SbaStockExchangeApplication.class);
        //return super.configure(builder);
    }
}
