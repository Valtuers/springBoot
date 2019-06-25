package com.lmc.service.impl;

import com.lmc.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqServiceImpl implements ActiveMqService {
    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void sendMsg(String message) {
        System.out.println("发送消息【"+message+"】");
        jmsTemplate.convertAndSend(message);
        //自定义发送地址
        //jmsTemplate.convertAndSend("yourAddress",message);
    }
    //使用注解，监听地址发送过来的消息
    @JmsListener(destination = "${spring.jms.template.default-destination}")
    @Override
    public void receiveMsg(String message) {
        System.out.println("接受到的消息【"+message+"】");
    }
}
