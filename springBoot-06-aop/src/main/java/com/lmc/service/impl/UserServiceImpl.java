package com.lmc.service.impl;

import com.lmc.service.UserService;
import com.lmc.bean.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public void printUser(User user) {
        if(user == null){
            throw new RuntimeException("用户为空！！！");
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
    }
}
