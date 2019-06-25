package com.lmc.controller;

import com.lmc.bean.User;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

    @RequestMapping("/ariE")
    public void ariE(){
        throw new ArithmeticException();
    }

    /**
     * JSR-303错误验证
     */
    @RequestMapping("/addUser")
    public Map<String,Object> addUser(@Valid User user, Errors errors){
        Map<String,Object> errMap = new HashMap<>();
        //获取错误列表
        List<ObjectError> oes = errors.getAllErrors();
        for (ObjectError oe : oes){
            String key = null;
            String msg = null;
            if( oe instanceof FieldError){
                FieldError fe = (FieldError)oe;
                key = fe.getField(); //获取错误字段名
            }else{
                //非字段错误
                key = oe.getObjectName();
            }
            msg = oe.getDefaultMessage();
            errMap.put(key,msg);
        }
        System.out.println(errMap);
        return errMap;
    }

    /**
     * @PathVariable 获取路径值
     */
    @RequestMapping(path = "/path/{id}/{name}",method = RequestMethod.GET)
    public Map<String,String> pathValue(@PathVariable String id,@PathVariable String name){
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        return map;
    }

    /**
     * @RequestParam 可设置默认值，使用于分页等。。。
     */
    @RequestMapping(path = "/get")
    public Map<String,String> pathValue2(@RequestParam(defaultValue = "lmc",name="id") String id,String name){
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        return map;
    }

    /**
     * @RequestBody 需要注意指定http头为 content-type为application/json
     * 使用body传输数据
     */
    @RequestMapping(value = "/bean",method = RequestMethod.POST)
    public User beanValue(@RequestBody User user){
        User user1 = new User(){{
            setBirthday(new Date());
        }};
        return user1;
    }

    /**
     * 获取http头信息
     */
    @RequestMapping(value = "/header")
    public String headerValue(@RequestHeader("User-Agent") String token){
        return token;
    }
}