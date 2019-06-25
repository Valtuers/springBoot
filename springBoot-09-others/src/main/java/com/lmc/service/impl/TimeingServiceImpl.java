package com.lmc.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 定时任务类
 */
@Service
public class TimeingServiceImpl {
    int count1 = 1;
    int count2 = 1;
//    @Scheduled(fixedRate = 1000)
//    @Async
    public void job1() {
        System.out.println(Thread.currentThread().getName()+"-任务1："+count1);
        ++count1;
    }
//    @Scheduled(fixedRate = 1000)
//    @Async
    public void job2() {
        System.out.println(Thread.currentThread().getName()+"-任务2："+count2);
        ++count2;
    }
    @Scheduled(cron = "0 58 8 * * ?")
    @Async
    public void job3() {
        System.out.println(Thread.currentThread().getName()+"-任务2："+count2);
        ++count2;
    }
}
