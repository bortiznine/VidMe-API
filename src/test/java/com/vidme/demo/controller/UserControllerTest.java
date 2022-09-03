package com.vidme.demo.controller;

import com.vidme.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Value("${spring.datasource.url}")
    private String dbUrl;
@Test
    public void setUserServiceTest(){
        UserService ust= new UserService();
assertNotNull(ust);
    }

}