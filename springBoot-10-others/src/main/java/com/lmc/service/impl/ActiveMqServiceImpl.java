package com.lmc.service.impl;

import com.lmc.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * activeMQ发送消息实现类
 */
@Service
public class ActiveMqServiceImpl implements ActiveMqService {
    @Autowired
    JmsMessagingTemplate jmsTemplate;
    @Autowired
    Queue queue;
    @Autowired
    Topic topic;

    /**
     * 自定义队列地址
     */
    @Override
    public void sendMsgToQueue(Destination destination, final String message) {
        System.out.println("发送消息【"+message+"】");
        jmsTemplate.convertAndSend(destination,message);
    }

    /**
     * 从配置中获取队列地址
     */
    @Override
    public void sendMsgToQueue(String message) throws JMSException {
        System.out.println("发送消息【"+message+"】，队列名称："+queue.getQueueName());
        jmsTemplate.convertAndSend(queue,message);
    }

    @Override
    public void sendMsgToTopic(Destination destination, final String message) {
        System.out.println("发送消息【"+message+"】");
        jmsTemplate.convertAndSend(destination,message);
    }

    @Override
    public void sendMsgToTopic(String message) throws JMSException {
        System.out.println("发送消息【"+message+"】，专题名称："+topic.getTopicName());
        jmsTemplate.convertAndSend(topic,message);
    }
}
