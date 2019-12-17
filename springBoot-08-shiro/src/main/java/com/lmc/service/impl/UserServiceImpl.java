package com.lmc.service.impl;

import com.lmc.bean.Role;
import com.lmc.bean.User;
import com.lmc.dao.RoleMapper;
import com.lmc.dao.UserMapper;
import com.lmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public User findUserByUserId(int id) {
        return userMapper.findById(id);
    }

    @Override
    public User findUserRoleByUserName(String username) {
        User user = userMapper.findByUsername(username);
        if(user != null){
            List<Role> roles = roleMapper.findRoleByUserId(user.getId());
            user.setRoleList(roles);
        }
        return user;
    }

    @Override
    public User findUserRoleByUserId(int id) {
        User user = userMapper.findById(id);
        List<Role> roles = roleMapper.findRoleByUserId(id);
        user.setRoleList(roles);
        return user;
    }
}
