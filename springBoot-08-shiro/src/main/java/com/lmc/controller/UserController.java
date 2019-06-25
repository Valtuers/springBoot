package com.lmc.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/login";
    }

    @RequestMapping("/user")
    public String user(){
        return "/mapper/mapper";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "/admin/admin";
    }

    @RequestMapping("/noAuth")
    public String noAuth(){
        return "/noAuth";
    }

    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        /**
         * 使用Shiro编写认证操作
         */
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,
                new Md5Hash(password).toString());
        try{
            subject.login(token);
            //登陆成功
        }catch (UnknownAccountException e){
            //登陆失败:用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //登陆失败:密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }
        return "redirect:/";
    }
}
