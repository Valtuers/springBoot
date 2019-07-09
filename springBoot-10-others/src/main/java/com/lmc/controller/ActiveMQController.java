package com.lmc.controller;

import com.lmc.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.JMSException;

@Controller
public class ActiveMQController {

    @Autowired
    ActiveMqService activeMqService;

    //队列消息的发送
    @RequestMapping("/aQ")
    @ResponseBody
    public String sendQ(String message) throws JMSException {
        activeMqService.sendMsgToQueue(message);
        return message;
    }

    //专题消息的发送
    @RequestMapping("/aT")
    @ResponseBody
    public String sendT(String message) throws JMSException {
        //activeMqService.sendMsgToTopic(new ActiveMQTopic("user.topic"),message);  自定义发送地址
        activeMqService.sendMsgToTopic(message);
        return message;
    }
}
