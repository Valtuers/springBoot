package com.lmc.jms;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderConsumer {
    private String consumerGroup = "order_consumer_group";


    private DefaultMQPushConsumer consumer;

    public OrderConsumer() {
        this.consumer = new DefaultMQPushConsumer(consumerGroup);
        this.consumer.setNamesrvAddr(JmsConfig.nameServerAddr);
        this.consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        try {
            this.consumer.subscribe(JmsConfig.order_topic, "*");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        //并发消息
//        this.consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
//            String msg = new String(msgs.get(0).getBody());
//            System.out.printf("%s 收到新消息: %s %n", Thread.currentThread().getName(), msg);
//            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//        });
        //单线程消费
        //为每个queue加锁，同一个queue按顺序处理，不同queue可以并发处理
        this.consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                String msg = new String(msgs.get(0).getBody());
                System.out.printf("%s 收到新消息: %s %n", Thread.currentThread().getName(), msg);
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });


        start();
    }

    private void start(){
        try {
            this.consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public void shutdown(){
        this.consumer.shutdown();
    }

    public DefaultMQPushConsumer getConsumer(){
        return this.consumer;
    }

}
