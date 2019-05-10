package com.lmc.aspect.validate.impl;

import com.lmc.aspect.validate.UserValidator;
import com.lmc.bean.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidatorImpl implements UserValidator {
    @Override
    public boolean validator(User user) {
        System.out.println("引入新的接口："+UserValidator.class.getSimpleName());
        return user != null;
    }
}
