package com.lmc.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lmc.bean.User;
import com.lmc.dao.UserMapper;
import com.lmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public User findById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageInfo<User> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<User> list = new PageInfo<>(userMapper.selectAll());
        return list;
    }
}
