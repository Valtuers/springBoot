package com.lmc.service.user.impl;

import com.lmc.bean.user.User;
import com.lmc.dao.mapper.user.UserMapper;
import com.lmc.service.user.UserService;
import com.lmc.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public User findById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public User setUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        stringRedisTemplate.opsForValue().set("user",JsonUtils.objectToJson(user));
        System.out.println(stringRedisTemplate.opsForValue().get("user"));
        return user;
    }
}
