package com.lmc.service.user;

import com.lmc.bean.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id);
}