package com.lmc.service.impl;

import com.lmc.bean.User;
import com.lmc.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private static final Map<Integer,User> map = new HashMap<Integer,User>();

    static {
        map.put(1,new User(1,"陈一",11));
        map.put(2,new User(2,"梁二",22));
        map.put(3,new User(3,"张三",33));
        map.put(4,new User(4,"李四",44));
    }

    @Override
    public Mono<User> getUserById(int id) {
        return Mono.just(map.get(id));
    }

    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(map.values());
    }
}
