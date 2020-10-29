package com.lmc.controller;

import com.lmc.jms.JmsConfig;
import com.lmc.jms.TracProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TracController {

    @Autowired
    TracProducer tracProducer;

    //同步发送消息
    @RequestMapping("/sendTrac")
    public Object sendSync(String text,String params) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message(JmsConfig.trac_topic,"事务消息","事务消息"+params,("事务："+text).getBytes());
        message.setDelayTimeLevel(1);
        SendResult sendResult = tracProducer.getProducer().sendMessageInTransaction(message,params);
        System.out.println(sendResult);
        return sendResult;
    }
}
