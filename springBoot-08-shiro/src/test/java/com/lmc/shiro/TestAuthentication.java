package com.lmc.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * shiro的认证Authentication单元测试
 */
public class TestAuthentication {

    private SimpleAccountRealm accountRealm = new SimpleAccountRealm();

    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

    @Before
    public void init(){
        accountRealm.addAccount("aaa", "111");
        accountRealm.addAccount("bbb", "222");

        defaultSecurityManager.setRealm(accountRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
    }

    @Test
    public void testAuthentication(){

        //当前操作主体
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("bbb","222");

        subject.login(usernamePasswordToken);

        System.out.println("认证结果为："+subject.isAuthenticated());

    }
}
