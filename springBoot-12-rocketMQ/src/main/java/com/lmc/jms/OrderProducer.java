package com.lmc.jms;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {
    private String producerGroup = "order_producer_group";

    private DefaultMQProducer producer;

    public OrderProducer() {
        producer = new DefaultMQProducer(producerGroup);
        //多节点用;分割
        producer.setNamesrvAddr(JmsConfig.nameServerAddr);
        //设置主题队列数，默认是4
        producer.setDefaultTopicQueueNums(4);
        start();
    }

    //对象在使用之前必须调用一次，只能初始化一次
    private void start(){
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    //一般在应用上下文监听器关闭
    public void shutdown(){
        this.producer.shutdown();
    }

    public DefaultMQProducer getProducer(){
        return this.producer;
    }
}
