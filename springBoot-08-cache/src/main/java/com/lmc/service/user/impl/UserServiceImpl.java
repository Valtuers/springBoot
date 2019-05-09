package com.lmc.service.user.impl;

import com.lmc.bean.User;
import com.lmc.dao.UserMapper;
import com.lmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    @Cacheable(cacheNames = "user")
    public User findById(int id) {
        System.out.println(userMapper.selectById(id));
        return userMapper.selectById(id);
    }
}
