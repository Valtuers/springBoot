package com.lmc.service;

import com.github.pagehelper.PageInfo;
import com.lmc.bean.user.Role;
import com.lmc.bean.user.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> findUser(Map<String, Object> where);

    PageInfo<User> findByPage(int pageNum, int pageSize);

    int addUser(User user);

    void tranTest(User user, Role role);
}
