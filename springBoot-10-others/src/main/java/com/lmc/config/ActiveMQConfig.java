package com.lmc.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
@EnableJms
public class ActiveMQConfig {

    /**
     * 自定义Queue和Topic地址
     */
    @Bean
    public Queue queue(){
        return new ActiveMQQueue("user.queue");
    }

    @Bean
    public Topic topic(){
        return new ActiveMQTopic("user.topic");
    }
    /**
     * 默认不支持订阅模式,需要定义独立的jmsListenerContainer
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory connectionFactory){
        return new DefaultJmsListenerContainerFactory(){{
            setPubSubDomain(true);
            setConnectionFactory(connectionFactory);
        }};
    }
}
