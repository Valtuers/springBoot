package com.lmc.controller;

import com.lmc.bean.JsonData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public JsonData login(@RequestBody Map<String,String> user){
        String username = user.get("username");
        String password = user.get("password");

        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
//            token.setRememberMe(false);
            subject.login(token);

            return JsonData.buildSuccess(subject.getSession().getId());

        }catch (UnknownAccountException e){
            //登陆失败:用户名不存在
            return JsonData.buildError("用户名不存在");
        }catch (IncorrectCredentialsException e){
            //登陆失败:密码错误
            return JsonData.buildError("密码错误");
        }
    }

    @RequestMapping("/logout")
    public JsonData logout(){
        SecurityUtils.getSubject().logout();

        return JsonData.buildSuccess("退出成功");
    }

    @RequestMapping("/not_permit")
    public JsonData not_permit(){

        return JsonData.buildSuccess("需要授权");
    }

    @RequestMapping("/check")
    public JsonData check(){

        return JsonData.buildSuccess("查看用户");
    }
}
