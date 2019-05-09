package com.lmc.service.user;

import com.github.pagehelper.PageInfo;
import com.lmc.bean.user.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id);

    User setUser(String username,String password);

    PageInfo<User> userPage(int num);
}