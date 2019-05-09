package com.lmc.controller;

import com.github.pagehelper.PageInfo;
import com.lmc.bean.User;
import com.lmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/user")
    @ResponseBody
    public List<User> getUser(){
        return userService.findAll();
    }

    @RequestMapping("/userId")
    @ResponseBody
    public User getUserById(int id){
        System.out.println(id);
        return userService.findById(id);
    }

    @RequestMapping("/u")
    @ResponseBody
    public PageInfo<User> u(int num){
        return userService.findByPage(num,5);
    }
}
