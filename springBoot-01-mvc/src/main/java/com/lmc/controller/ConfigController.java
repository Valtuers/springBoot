package com.lmc.controller;

import com.lmc.bean.ConfigValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 在controller中获取配置文件中的值
 */
@Controller
@RequestMapping("/config")
@PropertySource("classpath:application.yml")
public class ConfigController {
    @Value("${server.port}")
    private String port;

    @Value("${spring.resources.static-locations}")
    private String staticLocations;

    @Autowired
    ConfigValue configValue;

    @RequestMapping("/port")
    @ResponseBody
    public String port(){
        return "配置文件中的端口号："+port;
    }

    @RequestMapping("/static")
    @ResponseBody
    public String staticLocations(){
        return "配置文件中的端口号："+staticLocations;
    }

    @RequestMapping("/value")
    @ResponseBody
    public ConfigValue value(){
        return configValue;
    }
}
