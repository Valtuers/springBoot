package com.lmc.service.impl;

import com.lmc.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async
    public void generateUser() {
        //异步服务返回值只能是void或Future
        System.out.println("异步Service方法线程名称："+Thread.currentThread().getName()+"------");
    }
}
