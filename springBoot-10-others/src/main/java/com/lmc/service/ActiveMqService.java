package com.lmc.service;


import javax.jms.Destination;
import javax.jms.JMSException;

public interface ActiveMqService {

    void sendMsgToQueue(Destination destination, String message) throws JMSException;

    void sendMsgToQueue(String message) throws JMSException;

    void sendMsgToTopic(Destination destination, String message);

    void sendMsgToTopic(String message) throws JMSException;
}
