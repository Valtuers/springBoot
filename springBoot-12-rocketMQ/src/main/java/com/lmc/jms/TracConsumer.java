package com.lmc.jms;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Component;

@Component
public class TracConsumer {
    private String consumerGroup = "trac_consumer_group";


    private DefaultMQPushConsumer consumer;

    public TracConsumer() {
        this.consumer = new DefaultMQPushConsumer(consumerGroup);
        this.consumer.setNamesrvAddr(JmsConfig.nameServerAddr);
        //默认是集群模式，可以更改为广播模式，但是广播模式不支持重试
        this.consumer.setMessageModel(MessageModel.BROADCASTING);
        this.consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        try {
            //设置消费的topic和tag
            this.consumer.subscribe(JmsConfig.trac_topic, "*");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        //并发消息
        this.consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            String msg = new String(msgs.get(0).getBody());
            System.out.printf("%s 收到新消息: %s %n", Thread.currentThread().getName(), msg);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        //单线程消费
        //为每个queue加锁，同一个queue按顺序处理，不同queue可以并发处理
//        this.consumer.registerMessageListener(new MessageListenerOrderly() {
//            @Override
//            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
//                String msg = new String(msgs.get(0).getBody());
//                System.out.printf("%s 收到新消息: %s %n", Thread.currentThread().getName(), msg);
//                return ConsumeOrderlyStatus.SUCCESS;
//            }
//        });


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
