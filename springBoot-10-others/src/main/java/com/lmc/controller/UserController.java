package com.lmc.controller;

import com.lmc.bean.User;
import com.lmc.dao.UserRepository;
import com.lmc.task.AsyncTask;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    AsyncTask asyncTask;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/async")
    @ResponseBody
    public String async(){
        asyncTask.generateUser();
        System.out.println("控制器请求线程名称："+Thread.currentThread().getName()+"++++++");
        return "";
    }

    /**
     * elasticsearch搜索框架的crud
     */
    @RequestMapping(value = "/elastic/save",method = RequestMethod.POST)
    @ResponseBody
    public String elasticSave(@RequestBody User user){
        userRepository.save(user);
        return "200";
    }

    @RequestMapping(value = "/elastic/find",method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> elasticFind(long id){
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("id",id);
        Iterable<User> search = userRepository.search(queryBuilder);
        return search;
    }

    @RequestMapping(value = "/elastic/update",method = RequestMethod.PUT)
    @ResponseBody
    public String elasticUpdate(@RequestBody User user){
        userRepository.save(user);
        return "200";
    }

    @RequestMapping(value = "/elastic/del",method = RequestMethod.DELETE)
    @ResponseBody
    public String elasticDel(long id){
        userRepository.deleteById(id);
        return "200";
    }

}
