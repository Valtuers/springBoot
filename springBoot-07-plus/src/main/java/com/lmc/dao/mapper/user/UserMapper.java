package com.lmc.dao.mapper.user;


import com.lmc.bean.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    List<User> selectAll();

    User selectById(int id);
}
