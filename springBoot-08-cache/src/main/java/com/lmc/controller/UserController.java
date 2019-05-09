package com.lmc.controller;

import com.lmc.bean.User;
import com.lmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/allUser")
    public List<User> allUser(){
        return userService.findAll();
    }

    @RequestMapping("/user")
    public User userByOne(int id){
        return userService.findById(id);
    }
}
