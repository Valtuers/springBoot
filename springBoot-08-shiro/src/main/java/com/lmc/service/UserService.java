package com.lmc.service;

import com.lmc.bean.User;

public interface UserService {

    User findUserByUserId(int id);

    User findUserRoleByUserName(String username);

    User findUserRoleByUserId(int id);
}
