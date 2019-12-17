package com.lmc.dao;

import com.lmc.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User findByUsername(@Param("username")String username);

    @Select("select id,username,password,salt from user where id=#{id}")
    User findById(@Param("id")int id);

    @Select("select * from user where username=#{username} and password=#{password}")
    User findByUsernameAndPwd(@Param("username")String username,@Param("password")String pwd);
}
