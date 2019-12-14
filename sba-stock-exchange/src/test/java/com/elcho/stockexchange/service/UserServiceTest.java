package com.elcho.stockexchange.service;

import com.elcho.stockexchange.entity.UserEntity;
import com.elcho.stockexchange.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testSave(){
        User user = new User();
        user.setUserName("user");
        user.setPassword("123456");
        user.setEmail("user@user.com");
        user.setMobile("0755880980");
        user.setConfirmed(true);
        user.setUserType("user");

        userService.save(user);
    }



}
