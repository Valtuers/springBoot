package com.lmc.config;

import com.lmc.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class SpringConfig {

    @Bean
    public User user(){
        User user = new User();
        user.setUsername("林立");
        user.setBirthday(new Date());
        return user;
    }
}
