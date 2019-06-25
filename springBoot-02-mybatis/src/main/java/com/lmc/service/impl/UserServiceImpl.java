package com.lmc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lmc.bean.user.Role;
import com.lmc.bean.user.User;
import com.lmc.dao.user.UserMapper;
import com.lmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findUser(Map<String, Object> where) {
        return userMapper.selectUserByWhere(where);
    }

    @Override
    public PageInfo<User> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<User> list = new PageInfo<>(userMapper.selectUserByWhere(new HashMap<>()));
        return list;
    }

    //当方法报错时，回滚数据库
    @Transactional()
    public int addUser(User user) {
        int i = userMapper.insertUser(user);
//        int b = 11/0;
        return i;
    }

    /**
     * propagation(传播行为)
     * isolation(隔离级别)
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public void tranTest(User user, Role role) {
        userMapper.updateUser(user);
        //模拟异常
        int i = 1/0;
        userMapper.updateRole(role);
    }
}
