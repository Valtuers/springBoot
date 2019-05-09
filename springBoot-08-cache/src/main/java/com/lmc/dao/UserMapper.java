package com.lmc.dao;


import com.lmc.bean.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    List<User> selectAll();

    User selectById(int id);
}
