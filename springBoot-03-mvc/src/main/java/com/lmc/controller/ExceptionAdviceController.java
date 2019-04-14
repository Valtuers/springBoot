package com.lmc.controller;
/**
 * 异常通知
 */

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdviceController {

    @ExceptionHandler(ArithmeticException.class)
    public String exception(Exception e){
        System.out.println("发生异常"+e.getMessage());
        return "error/404";
    }
    @ExceptionHandler(Exception.class)
    public String esception(Exception e){
        System.out.println("发生异常111"+e.getMessage());
        return "error/4xx";
    }
}
