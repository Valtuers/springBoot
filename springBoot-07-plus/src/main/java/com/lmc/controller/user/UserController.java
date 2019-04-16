package com.lmc.controller.user;

import com.lmc.bean.user.User;
import com.lmc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/allUser")
    @ResponseBody
    public List<User> allUser(){
        return userService.findAll();
    }

    @RequestMapping("/user")
    @ResponseBody
    public User userByOne(int id){
        return userService.findById(id);
    }

    @RequestMapping("/setUser")
    @ResponseBody
    public User setUser(String username,String password){
        return userService.setUser(username,password);
    }
}
