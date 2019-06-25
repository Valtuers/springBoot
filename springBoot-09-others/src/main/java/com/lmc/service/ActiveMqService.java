package com.lmc.service;

public interface ActiveMqService {
    void sendMsg(String message);

    void receiveMsg(String message);
}
