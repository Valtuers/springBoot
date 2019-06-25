package com.lmc.service.impl;

import com.lmc.bean.User;
import com.lmc.dao.UserMapper;
import com.lmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findUser(String username) {
        return userMapper.selectByName(username);
    }
}
