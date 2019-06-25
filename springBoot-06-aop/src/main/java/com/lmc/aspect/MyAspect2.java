package com.lmc.aspect;

import com.lmc.bean.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(4)//指定多个切面的顺序
public class MyAspect2 {
    //切点
    @Pointcut("execution(* com.lmc.service.impl.UserServiceImpl.printUser(..))")
    public void pointCut(){
    }

    //链接点
    @Before("pointCut() && args(user)")
    public void before(JoinPoint jp, User user){
        Object[] args = jp.getArgs();
        System.out.println("before2.....");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("around before2......");
        jp.proceed();
        System.out.println("around after2.......");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("after2.....");
    }

    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("afterReturning2.....");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing2.....");
    }
}
