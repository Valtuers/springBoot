package com.lmc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/home")
    @ResponseBody
    public String home(){
        return "/test/home";
    }

    @RequestMapping("/log")
    @ResponseBody
    public String log(){
        logger.info("这是info级别日志");
        logger.warn("这是warn级别日志");
        logger.error("这是error级别日志");
        return "";
    }
}
