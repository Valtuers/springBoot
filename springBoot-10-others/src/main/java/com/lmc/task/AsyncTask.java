package com.lmc.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//可标记为异步类，类的所有方法都为异步方法
@Async
@Component
public class AsyncTask {
    int count1 = 1;
    int count2 = 1;
    int count3 = 1;
    int count4 = 1;

    //@Async 也可单独标记方法
    public void generateUser() {
        //异步服务返回值只能是void或Future
        System.out.println("异步generateUser方法线程名称："+Thread.currentThread().getName()+"------");
    }

    @Scheduled(fixedRate = 1000)
    public void job1() {
        System.out.println(Thread.currentThread().getName()+"-任务1："+count1);
        ++count1;
    }
    @Scheduled(fixedRate = 1000)
    public void job2() {
        System.out.println(Thread.currentThread().getName()+"-任务2："+count2);
        ++count2;
    }
    @Scheduled(fixedRate = 1000)
    public void job3() {
        System.out.println(Thread.currentThread().getName()+"-任务3："+count3);
        ++count3;
    }
    @Scheduled(cron = "0 7 16 * * ?")
    public void job4() {
        System.out.println(Thread.currentThread().getName()+"-任务4："+count4);
        ++count4;
    }
}
