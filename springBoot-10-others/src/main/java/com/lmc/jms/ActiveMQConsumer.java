package com.lmc.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQConsumer {

    //使用注解，监听地址发送过来的队列消息
    @JmsListener(destination = "user.queue")
    public void receiveMsgByQueue(String message) {
        System.out.println("消费者接收到的队列消息【"+message+"】");
    }

    /**
     * 订阅模式：有三个订阅者接收信息,有一个没有配置containerFactory无法接收
     * @param message
     */
    @JmsListener(destination = "user.topic",containerFactory = "jmsListenerContainerTopic")
    public void receiveMsgByTopic1(String message) {
        System.out.println("订阅者1接收到的专题消息【"+message+"】");
    }

    @JmsListener(destination = "user.topic",containerFactory = "jmsListenerContainerTopic")
    public void receiveMsgByTopic2(String message) {
        System.out.println("订阅者2接收到的专题消息【"+message+"】");
    }

    //没指定自定义的jmsListenerContainerTopic，无法获取订阅消息
    @JmsListener(destination = "user.topic")
    public void receiveMsgByTopic3(String message) {
        System.out.println("订阅者3接收到的专题消息【"+message+"】");
    }
}
