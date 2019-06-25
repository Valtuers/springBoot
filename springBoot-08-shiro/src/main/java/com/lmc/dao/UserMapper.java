package com.lmc.dao;

import com.lmc.bean.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    User selectByName(String username);
}
