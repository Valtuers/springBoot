package com.lmc.config;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Set;

public class UserRolesFilter extends AuthorizationFilter {

    @SuppressWarnings({"unchecked"})
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        Subject subject = getSubject(request, response);

        //获取当前访问资源所需要的角色数组
        String[] rolesArray = (String[]) mappedValue;
        //没有角色限制则可以直接访问
        if (rolesArray == null || rolesArray.length == 0) {
            //no roles specified, so nothing to check - allow access.
            return true;
        }
        //只要符合其中一个角色即可访问
        Set<String> roles = CollectionUtils.asSet(rolesArray);
        for(String role: roles){
            if(subject.hasRole(role)){
                return true;
            }
        }
        return false;
    }

}
