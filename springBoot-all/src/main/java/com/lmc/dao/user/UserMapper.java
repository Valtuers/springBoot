package com.lmc.dao.user;

import com.lmc.bean.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    User selectUserByName(String username);

    User selectUserById(Integer id);

    List<User> selectAllUser();
}
