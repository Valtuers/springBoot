package com.lmc.controller;

import com.lmc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping("/toError")
    @ResponseBody
    public String toLogin(int num){
        int b = 5/num;
        return "123";
    }

    //JSR-303错误验证
    @RequestMapping("/addUser")
    @ResponseBody
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
}
