package com.lmc.controller.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdviceController {

    @ExceptionHandler(Exception.class)
    public String exception(Exception e){
        System.out.println("发生异常:"+e.getMessage());
        return "/error/5xx";
    }

}
