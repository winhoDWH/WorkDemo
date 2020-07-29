package com.dwh.payweb.entity;

import com.dwh.payweb.Service.Imp.UserServiceImp;
import com.dwh.payweb.Service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userServiceImp;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usertoken = (UsernamePasswordToken) token;
        Business user = userServiceImp.getPassword(usertoken.getUsername());
        if(user==null){
            return null;
        }
        return new SimpleAuthenticationInfo("",user.getPassword(),getName());
    }
}
