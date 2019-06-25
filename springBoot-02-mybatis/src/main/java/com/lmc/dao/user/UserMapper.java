package com.lmc.dao.user;

import com.lmc.bean.user.Role;
import com.lmc.bean.user.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 使用注解或xml编写sql语句
 */
@Component
public interface UserMapper {

    List<User> selectUserByWhere(Map<String,Object> where);

    //@Insert("insert into user (username,password,sex) values(#{username},#{password},#{sex})")
    //@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertUser(User user);

    //@Update("update user set username=#{username} where id=#{id}")
    int updateUser(User user);

    //@Update("update role set name=#{name} where id=#{id}")
    int updateRole(Role role);

    //@Delete("delete from user where id=#{id}")
    int delUser(int id);
}
