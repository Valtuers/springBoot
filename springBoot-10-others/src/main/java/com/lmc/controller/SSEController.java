package com.lmc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SSEController {

    @RequestMapping(value = "/sse",produces = "text/event-stream;charset=UTF-8")
    public String push(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:服务端推送:"+Math.random()*100+"\n\n";//必须以data:开头；以\n\n结尾
    }
}
