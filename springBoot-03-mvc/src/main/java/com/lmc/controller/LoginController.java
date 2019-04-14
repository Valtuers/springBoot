package com.lmc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping("/toLogin")
    @ResponseBody
    public String toLogin(int num){
        int b = 5/num;
        return "123";
    }
}
