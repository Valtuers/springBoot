package com.lmc.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * shiro的realm单元测试
 */
public class TestRealm {

    @Before
    public void init(){

    }

    @Test
    public void testIniRealm(){
        //创建SecurityManager工厂，通过配置文件ini创建
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();

        //讲securityManager设置到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);


        //当前操作主体
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("bbb","222");

        subject.login(usernamePasswordToken);

        System.out.println("认证结果为："+subject.isAuthenticated());

        //查看是否有对应角色
        System.out.println(subject.hasRole("root"));
        System.out.println(subject.hasRole("admin"));
        System.out.println(subject.hasRole("user"));
        //查看角色的权限
        System.out.println("是否拥有权限："+subject.isPermitted("video:find"));

        //查看用户名
        System.out.println(subject.getPrincipal());

        subject.logout();
        System.out.println("logout后认证结果为："+subject.isAuthenticated());
    }


    @Test
    public void testJdbcRealm(){
        /**
         * ini文件连接数据库
         */
//        //创建SecurityManager工厂，通过配置文件ini创建
//        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:jdbcrealm.ini");
//        SecurityManager securityManager = factory.getInstance();
//
//        //讲securityManager设置到当前运行环境中
//        SecurityUtils.setSecurityManager(securityManager);

        /**
         * 代码连接数据库
         */
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/demo?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false");
        ds.setUsername("root");
        ds.setPassword("root");

        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setPermissionsLookupEnabled(true);
        jdbcRealm.setDataSource(ds);

        securityManager.setRealm(jdbcRealm);
        SecurityUtils.setSecurityManager(securityManager);

        //当前操作主体
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("bbb","222");

        subject.login(usernamePasswordToken);

        System.out.println("认证结果为："+subject.isAuthenticated());

        //查看是否有对应角色
        System.out.println(subject.hasRole("root"));
        System.out.println(subject.hasRole("admin"));
        System.out.println(subject.hasRole("user"));
        //查看角色的权限
        System.out.println("是否拥有权限："+subject.isPermitted("video:find"));

        //查看用户名
        System.out.println(subject.getPrincipal());

        subject.logout();
        System.out.println("logout后认证结果为："+subject.isAuthenticated());
    }

    @Test
    public void testMyRealm(){
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(new MyRealm());
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("bbb","222");
        subject.login(token);

        System.out.println("认证结果："+subject.isAuthenticated());

        System.out.println("对应角色："+subject.hasRole("user"));
        System.out.println("对应权限："+subject.isPermitted("video:add"));
    }


    class MyRealm extends AuthorizingRealm {

        final Map<String,String> users = new HashMap<String,String>(){{
            put("aaa", "111");
            put("bbb", "222");
        }};

        final Map<String,Set<String>> permits = new HashMap<String,Set<String>>(){{
            Set<String> set1 = new HashSet<>();
            set1.add("video:delete");
            set1.add("video:add");

            Set<String> set2 = new HashSet<>();
            set2.add("video:buy");
            set2.add("video:find");

            put("aaa", set1);
            put("bbb", set2);
        }};

        final Map<String,Set<String>> roles = new HashMap<String,Set<String>>(){{
            Set<String> set1 = new HashSet<>();
            set1.add("root");

            Set<String> set2 = new HashSet<>();
            set2.add("user");

            put("aaa", set1);
            put("bbb", set2);
        }};

        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
            System.out.println("执行授权");
            String name = (String)principals.getPrimaryPrincipal();

            Set<String> permit = permits.get(name);
            Set<String> role = roles.get(name);

            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.setRoles(role);
            info.setStringPermissions(permit);
            return info;
        }

        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
            System.out.println("执行认证");
            String name = (String)token.getPrincipal();
            String pwd = users.get(name);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(name,pwd,this.getName());
            return info;
        }
    }
}
