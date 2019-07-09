package com.lmc.controller;

import com.lmc.bean.User;
import com.lmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class FluxController {

    @Autowired
    private UserService userService;

    @RequestMapping("/id/{id}")
    public Mono<User> test1(@PathVariable int id){
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/all",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> test2(){
        return userService.getAllUser().delayElements(Duration.ofSeconds(1));
    }
}
