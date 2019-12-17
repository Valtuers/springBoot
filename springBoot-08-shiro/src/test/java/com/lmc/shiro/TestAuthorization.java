package com.lmc.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * shiro的认证Authorization单元测试
 */
public class TestAuthorization {

    private SimpleAccountRealm accountRealm = new SimpleAccountRealm();

    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

    @Before
    public void init(){
        accountRealm.addAccount("aaa", "111","root","admin");
        accountRealm.addAccount("bbb", "222","user");

        defaultSecurityManager.setRealm(accountRealm);
    }

    @Test
    public void testAuthorization(){
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //当前操作主体
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("bbb","222");

        subject.login(usernamePasswordToken);

        System.out.println("认证结果为："+subject.isAuthenticated());

        //查看是否有对应角色
        System.out.println(subject.hasRole("root"));
        System.out.println(subject.hasRole("admin"));
        System.out.println(subject.hasRole("user"));
        //查看用户名
        System.out.println(subject.getPrincipal());

        subject.logout();
        System.out.println("logout后认证结果为："+subject.isAuthenticated());
    }
}
