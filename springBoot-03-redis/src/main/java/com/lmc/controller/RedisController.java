package com.lmc.controller;

import com.lmc.bean.User;
import com.lmc.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class RedisController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/string")
    //字符串操作
    @ResponseBody
    public List<String> testString(){
        List<String> result = new ArrayList<>();
        stringRedisTemplate.opsForValue().set("string1","陈一");
        stringRedisTemplate.opsForValue().set("string2","张三");
        User user = new User(){{
            setName("李四");
            setAge(22);
        }};
        stringRedisTemplate.opsForValue().set("string3", JsonUtils.objectToJson(user));

        result.add(stringRedisTemplate.opsForValue().get("string1"));
        result.add(stringRedisTemplate.opsForValue().get("string2"));
        result.add(stringRedisTemplate.opsForValue().get("string3"));
        return result;
    }


    @RequestMapping("/list")
    @ResponseBody
    //类似栈的数据结构
    public List<String> testList(){
        List<String> result = new ArrayList<>();
        //一次从左插入多个数据
        stringRedisTemplate.opsForList().leftPushAll("list1","v1","v2","v3");
        //一次从右插入多个数据
        stringRedisTemplate.opsForList().rightPushAll("list2","林铭聪","v2","v3");
        //绑定list1链表操作
        BoundListOperations<String, String> list1 = stringRedisTemplate.boundListOps("list1");
        //绑定list2链表操作
        BoundListOperations<String, String> list2 = stringRedisTemplate.boundListOps("list2");
        //获取所有list1数据
        List all = list1.range(0,list1.size()-1);
        //获取list1链表长度
        result.add(String.valueOf(list1.size()));
        //弹出最右数据
        result.add(list1.rightPop());
        result.add(String.valueOf(list1.size()));
        result.add(String.valueOf(list2.size()));
        //弹出最左数据
        result.add(list2.leftPop());
        //从左插入一个数据
        list2.leftPush("v1");
        result.add(list2.leftPop());
        result.add(String.valueOf(list2.size()));
        return result;
    }

    @RequestMapping("/set")
    @ResponseBody
    public Set testSet(){
        List<String> result = new ArrayList<>();
        //注意：set不允许重复，所以只有一个s1被添加
        stringRedisTemplate.opsForSet().add("set1","s1","s1","s2","s3");
        stringRedisTemplate.opsForSet().add("set2","s4","s5","s6","s7");
        //绑定set1集合操作
        BoundSetOperations setOps = stringRedisTemplate.boundSetOps("set1");
        //增加两个元素
        setOps.add("s4","s5");
        //删除两个元素
        setOps.remove("s1","s2");
        //返回所有元素
        Set all = setOps.members();
        //set成员数
        Long size = setOps.size();
        //求交集
        Set inter = setOps.intersect("set2");
        //求交集，并且用新集合inter2保存
        setOps.intersectAndStore("set2","inter2");
        //求差集
        Set diff = setOps.diff("set2");
        //求差集，并且用新集合diff2保存
        setOps.diffAndStore("set2","diff2");
        //求并集
        Set union = setOps.union("set2");
        //求并集，并且用新集合union2保存
        setOps.unionAndStore("set2","union2");
        return inter;
    }
}
