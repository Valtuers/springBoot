package com.lmc.controller;

import com.lmc.bean.Order;
import com.lmc.jms.JmsConfig;
import com.lmc.jms.OrderProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderProducer orderProducer;

    //同步发送消息
    @RequestMapping("/sendSync")
    public Object sendSync(String text) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message(JmsConfig.topic,"同步消息",("支付："+text).getBytes());
        message.setDelayTimeLevel(1);
        SendResult sendResult = orderProducer.getProducer().send(message);
        System.out.println(sendResult);
        return sendResult;
    }

    //异步发送消息
    @RequestMapping("/sendAsync")
    public void sendAsync(String text) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message(JmsConfig.topic,"异步消息",("支付："+text).getBytes());
        message.setDelayTimeLevel(1);
        orderProducer.getProducer().send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.printf("发送状态=%s，内容=%s\n",sendResult.getSendStatus(),sendResult);
            }

            @Override
            public void onException(Throwable e) {

            }
        });
    }

    //一次性发送消息
    @RequestMapping("/sendOneWay")
    public void sendOneWay(String text) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message(JmsConfig.topic,"一次性消息",("支付："+text).getBytes());
        orderProducer.getProducer().sendOneway(message);
    }


    //指定队列发送消息
    @RequestMapping("/sendSeletctQueue")
    public Object sendSeletctQueue(String text) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message(JmsConfig.topic,"指定队列消息",("支付："+text).getBytes());
        message.setDelayTimeLevel(1);
        SendResult sendResult = orderProducer.getProducer().send(message, new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                int index = Integer.parseInt(arg.toString());
                System.out.println("指定前的队列："+index);
                return mqs.get(index);
            }
        },2);
        System.out.println(sendResult);
        return sendResult;
    }

    //按顺序发送消息
    @RequestMapping("/sendOrderly")
    public Object sendOrderly() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        List<Order> orderList = Order.getOrderList();
        List<SendResult> sendResultList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            Message message = new Message(JmsConfig.order_topic,"顺序消息",String.valueOf(order.getOrderId()),order.toString().getBytes());
            SendResult sendResult = orderProducer.getProducer().send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    long id = (long) arg;
                    long index = id % mqs.size();
                    return mqs.get((int)index);
                }
            }, order.getOrderId());
            sendResultList.add(sendResult);
            System.out.printf("发送状态=%s，内容=%s，type=%s\n",sendResult.getSendStatus(),sendResult,order.getType());
        }
        return sendResultList;
    }
}
