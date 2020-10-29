package com.lmc.jms;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class TracProducer {
    private String producerGroup = "Trac_producer_group";

    private TransactionMQProducer producer;

    private TransactionListener transitionListener = new TransactionListenerImpl();

    private ExecutorService executorService = new ThreadPoolExecutor(2,5,100, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(2000), r -> {
        Thread thread = new Thread(r);
        thread.setName("线程1");
        return thread;
    });

    public TracProducer() {
        producer = new TransactionMQProducer(producerGroup);
        //多节点用;分割
        producer.setNamesrvAddr(JmsConfig.nameServerAddr);

        producer.setTransactionListener(transitionListener);

        //设置主题队列数，默认是4
        producer.setDefaultTopicQueueNums(4);

        producer.setExecutorService(executorService);


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

    public TransactionMQProducer getProducer(){
        return this.producer;
    }

}

class TransactionListenerImpl implements TransactionListener {

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object params) {
        System.out.println("===executeLocalTransaction===");
        System.out.printf("body=%s,id=%s,key=%s\n",msg.getBody(),msg.getTransactionId(),msg.getKeys());

        //TODO 执行本地事务
        //TODO 结束本地事务

        int status = Integer.parseInt(params.toString());
        //二次确认消息，然后消费者可消息
        if(status == 1){
            return LocalTransactionState.COMMIT_MESSAGE;
        }
        //回滚消息，broker会消除半消息
        else if(status == 2){
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        //broker会进行回查消息
        else if(status == 3){
            return LocalTransactionState.UNKNOW;
        }
        return null;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        System.out.println("===checkLocalTransaction===");
        System.out.printf("body=%s,id=%s,key=%s\n",msg.getBody(),msg.getTransactionId(),msg.getKeys());

        //要么提交，要么回滚
        //根据keys检查本地事务是否完成
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
