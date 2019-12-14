package com.elcho.stockexchange.dao;

import com.elcho.stockexchange.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave(){
        UserEntity user = new UserEntity();
        user.setUserName("Admin");
        user.setPassword("123456");
        user.setEmail("admin@admin.com");
        user.setMobile("00000000");
        user.setConfirmed(true);
        user.setUserType("Admin");

        userRepository.save(user);
    }

}
