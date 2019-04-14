package com.lmc.config;
/**
 * 自定义创建dbcp数据源，并从配置文件读取属性
 */

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    //先把该对象加入到ioc容器中
    @Bean
    //从application.yml中读取spring.datasource的属性，并注入到数据源中
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return new BasicDataSource();
    }
}
