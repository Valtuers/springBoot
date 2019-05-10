package com.lmc.controller;

import com.lmc.aspect.validate.UserValidator;
import com.lmc.bean.User;
import com.lmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/print")
    @ResponseBody
    public User printUser(){
        User user = new User();
        user.setUsername("林铭聪");
        user.setPassword("1234");
        userService.printUser(user);
        return user;
    }

    @RequestMapping("/vprint")
    @ResponseBody
    public User validatorAndPrint(){
        User user = new User();
        user.setUsername("林铭聪");
        user.setPassword("1234");
        UserValidator userValidator = (UserValidator)userService;
        if(userValidator.validator(user)){
            userService.printUser(user);
        }
        return user;
    }
}
