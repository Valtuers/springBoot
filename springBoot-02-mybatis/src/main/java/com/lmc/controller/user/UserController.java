package com.lmc.controller.user;

import com.github.pagehelper.PageInfo;
import com.lmc.bean.user.Role;
import com.lmc.bean.user.User;
import com.lmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/find")
    @ResponseBody
    public List<User> getUser(@RequestBody Map<String,Object> where){
        return userService.findUser(where);
    }

    @RequestMapping("/page/{page}")
    @ResponseBody
    public PageInfo<User> pageData(@PathVariable("page") int page){
        return userService.findByPage(page,5);
    }

    @RequestMapping("/add")
    @ResponseBody
    public int addUser(){
        User user = new User();
        user.setUsername("李四");
        user.setPassword("444");
        user.setSex(1);
        int count = userService.addUser(user);
        return user.getId();
    }

    @RequestMapping("/tran")
    @ResponseBody
    public int tranTest(){
        User user = new User();
        user.setId(1);
        user.setUsername("chenyi");
        Role role = new Role();
        role.setId(1);
        role.setName("ooo");
        userService.tranTest(user,role);
        return user.getId();
    }
}
