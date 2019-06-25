package com.lmc.controller.user;

import com.github.pagehelper.PageInfo;
import com.lmc.bean.user.User;
import com.lmc.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/getAllUser")
    @ResponseBody
    public PageInfo<User> getAllUser(@RequestBody Map<String,Object> data){
        return userService.findAllUser((Integer)data.get("pageNum"),(Integer)data.get("pageSize"));
    }


    @RequestMapping("/loginUser")
    @ResponseBody
    public Integer loginUser(@RequestBody Map<String,Object> form){
        /**
         * 使用Shiro编写认证操作
         */
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(form.get("username").toString(),
                new Md5Hash(form.get("password")).toString());
        try{
            subject.login(token);
            //登陆成功
        }catch (UnknownAccountException e){
            //登陆失败:用户名不存在
            return 1002;
        }catch (IncorrectCredentialsException e){
            //登陆失败:密码错误
            return 1003;
        }
        return 1001;
    }

    @RequestMapping("/logOut")
    public String logOut(){
        SecurityUtils.getSubject().logout();
        return "/login";
    }
}
