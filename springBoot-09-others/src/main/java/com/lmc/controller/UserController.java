package com.lmc.controller;

import com.lmc.service.ActiveMqService;
import com.lmc.service.AsyncService;
import com.lmc.service.impl.TimeingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    AsyncService asyncService;
    @Autowired
    ActiveMqService activeMqService;
    @Autowired
    TimeingServiceImpl timeingService;

    @RequestMapping("/async")
    @ResponseBody
    public String async(){
        asyncService.generateUser();
        System.out.println("控制器请求线程名称："+Thread.currentThread().getName()+"++++++");
        return "";
    }

    //普通消息的发送
    @RequestMapping("/send")
    @ResponseBody
    public String send(String message){
        activeMqService.sendMsg(message);
        return message;
    }

}
