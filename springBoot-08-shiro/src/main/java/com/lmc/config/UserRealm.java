package com.lmc.config;

import com.lmc.bean.Permission;
import com.lmc.bean.Role;
import com.lmc.bean.User;
import com.lmc.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     * 进行权限校验
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权");

        User s_user = (User) principals.getPrimaryPrincipal();
        User user = userService.findUserRoleByUserName(s_user.getUsername());

        List<String> roleList = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();

        for(Role r:user.getRoleList()){
            roleList.add(r.getName());

            for (Permission p: r.getPermissionList()){
                if(p!=null){
                    permissionList.add(p.getName());
                }
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleList);
        simpleAuthorizationInfo.addStringPermissions(permissionList);
        return simpleAuthorizationInfo;
    }

    /**
     * 进行登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证");
        String username = (String) token.getPrincipal();
        User user = userService.findUserRoleByUserName(username);
//        System.out.println(new SimpleHash("md5",user.getPassword(),null,2));
        if(user != null){
            String pwd = user.getPassword();
            if(pwd == null || "".equals(pwd)){
                return null;
            }
            return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
        }
        return null;
    }
}
