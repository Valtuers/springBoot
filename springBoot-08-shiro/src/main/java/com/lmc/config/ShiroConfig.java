package com.lmc.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        System.out.println("执行");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //必须设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //访问需要登录的接口却没有登录，则调用该接口
        shiroFilterFactoryBean.setLoginUrl("/pub/toLogin");

        //登录成功跳转接口
        shiroFilterFactoryBean.setSuccessUrl("/");

        //没有授权接口
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/not_permit");

        //配置自定义filter
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("rolesFilter", new UserRolesFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        /**
         * Shiro内置拦截器，可以实现权限相关的拦截器
         * anon:无需拦截(登陆)可以访问
         * authc:必须验证才可以访问
         * role:该资源必须得到角色权限才可以访问
         * perms:该资源必须得到资源权限才可以访问
         * mapper:如果使用rememberMe功能可以直接访问
         */
        Map<String,String> permitMap = new LinkedHashMap<>();
        //退出登录
        permitMap.put("/user/logout", "logout");
        //公共访问路由
        permitMap.put("/pub/**", "anon");
        permitMap.put("/user/login", "anon");
        //必须登录才能访问
        permitMap.put("/user/**", "authc");
        //管理员角色才能访问，配置了自定义过滤器rolesFilter
        permitMap.put("/admin/**", "rolesFilter[admin]");
        //编辑权限才能访问
        permitMap.put("/video/update", "perms[video_update]");

        permitMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(permitMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        defaultWebSecurityManager.setRealm(userRealm());
        //如果不是前后端分离则不必设置SessionManager
        defaultWebSecurityManager.setSessionManager(sessionManager());
        //配置redis
        defaultWebSecurityManager.setCacheManager(redisCacheManager());
        return defaultWebSecurityManager;
    }

    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();

        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());

        return userRealm;
    }

    @Bean
    public SessionManager sessionManager() {
        UserSessionManager userSessionManager = new UserSessionManager();

        //session过期时间(毫秒)，默认30分钟
        userSessionManager.setGlobalSessionTimeout(2000000);
        //配置session持久化
        userSessionManager.setSessionDAO(redisSessionDAO());
        return userSessionManager;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        //使用散列算法(md5)
        hashedCredentialsMatcher.setHashAlgorithmName("md5");

        //散列2次
        hashedCredentialsMatcher.setHashIterations(2);

        return hashedCredentialsMatcher;
    }

    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //设置过期时间,单位是秒
        redisCacheManager.setExpire(20);
        return redisCacheManager;
    }
    /**
     * 配置redisManager
     */
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("localhost");
        redisManager.setPort(6379);
        return redisManager;
    }

    /**
     * 自定义session持久化
     * @return
     */
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionIdGenerator(new UserSessionIdGenerator());
        return redisSessionDAO;
    }

    /**
     * 可以在Controller中加入AOP注解
     * 例如:@RequestGuest,@RequestRoles('admin')
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 管理shiro一些bean的生命周期，即bean初始化与摧毁
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    /**
     * 用来扫描上下文所有的Advisor,将符合条件的Advisor应用到Bean中,需要依赖lifecycleBeanPostProcessor才可以创建
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
     */
//    @Bean
//    public ShiroDialect getShiroDialect(){
//        return new ShiroDialect();
//    }
}
