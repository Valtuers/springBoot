package com.lmc.aspect;

import com.lmc.aspect.validate.impl.UserValidatorImpl;
import com.lmc.aspect.validate.UserValidator;
import com.lmc.bean.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面类
 */
@Aspect
@Component
@Order(3)//指定多个切面的顺序
public class MyAspect {
    //引入新的类来增强服务
    @DeclareParents(value = "com.lmc.service.impl.UserServiceImpl",
            defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;

    //切点
    @Pointcut("execution(* com.lmc.service.impl.UserServiceImpl.printUser(..))")
    public void pointCut(){
    }

    //链接点
    @Before("pointCut() && args(user)")
    public void before(JoinPoint jp, User user){
        Object[] args = jp.getArgs();
        System.out.println("before.....");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("around before......");
        jp.proceed();
        System.out.println("around after.......");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("after.....");
    }

    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("afterReturning.....");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing.....");
    }

}
