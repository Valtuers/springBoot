package com.lmc.controller;


import com.lmc.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {

    @Autowired
    User user;

    @RequestMapping("/hello")
    @ResponseBody
    public User hello(){
        return user;
    }
}
