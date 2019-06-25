package com.lmc.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 把配置文件的值用包装类包装
 */
@Component
@PropertySource("classpath:application.yml")
@ConfigurationProperties
public class ConfigValue {
    @Value("${server.port}")
    private String port;

    @Value("${spring.resources.static-locations}")
    private String staticLocations;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getStaticLocations() {
        return staticLocations;
    }

    public void setStaticLocations(String staticLocations) {
        this.staticLocations = staticLocations;
    }
}
