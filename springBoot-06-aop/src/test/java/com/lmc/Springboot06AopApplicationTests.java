package com.lmc;

import com.lmc.aspect.validate.UserValidator;
import com.lmc.bean.User;
import com.lmc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot06AopApplicationTests {
    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setPassword("1234");
        user.setUsername("asdasdasd");
        UserValidator userValidator = (UserValidator)userService;
        System.out.println(userValidator.validator(user));
    }

}
