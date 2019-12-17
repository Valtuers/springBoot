package com.lmc.controller;

import com.lmc.bean.User;
import com.lmc.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserService userService;

    @RequestMapping("/userRole")
    public User userRole(@Param("id")int id){
        User user = userService.findUserRoleByUserId(id);
        return user;
    }
}
