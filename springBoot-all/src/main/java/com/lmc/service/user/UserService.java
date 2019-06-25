package com.lmc.service.user;

import com.github.pagehelper.PageInfo;
import com.lmc.bean.user.User;

import java.util.List;

public interface UserService {
    User findUserByName(String username);

    User findUserById(Integer id);

    PageInfo<User> findAllUser(int pageNum, int pageSize);
}
