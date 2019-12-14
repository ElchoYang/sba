package com.elcho.stockexchange.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void initMockMvc() {
        //初始化MockMvc
        this.mockMvc = webAppContextSetup(wac).build();
        //如果针对单个控制器的测试
        /*EmployeeController ec = new EmployeeController();
        this.mockMvc = standaloneSetup(ec).build();*/
    }

    @Test
    public void testList() throws Exception {
        //通过mockMvc执行请求
        mockMvc.perform(get("/api/users"))
                .andExpect(status().is(200))  //做断言
                .andDo(print());  //打印结果
    }

    @Test
    public void testgetUsers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/api/users/user"))
                .andExpect(status().is(200))
                //.andExpect(content().contentType("application/json;UTF-8"))
                .andExpect(content().json("{\"id\":4,\"userName\":\"user\",\"password\":\"123456\",\"userType\":\"user\",\"email\":\"user@user.com\",\"mobile\":\"0755880980\",\"confirmed\":true}"))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }


}
