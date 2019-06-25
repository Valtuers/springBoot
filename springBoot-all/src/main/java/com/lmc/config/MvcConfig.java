package com.lmc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/home").setViewName("/home.html");
        registry.addViewController("/mapper").setViewName("/mapper.html");
        registry.addViewController("/admin").setViewName("/admin.html");
        registry.addViewController("/user").setViewName("/user.html");
        registry.addViewController("/login").setViewName("/login.html");
        registry.addViewController("/register").setViewName("/register.html");
        registry.addViewController("/noAuth").setViewName("/noAuth.html");
    }

}
