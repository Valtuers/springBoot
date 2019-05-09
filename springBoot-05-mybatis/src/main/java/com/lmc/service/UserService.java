package com.lmc.service;

import com.github.pagehelper.PageInfo;
import com.lmc.bean.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id);

    PageInfo<User> findByPage(int pageNum, int pageSize);
}
