package com.dwh.payweb.Controller;

import com.dwh.payweb.Request.RequestObj;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/login")
    public RequestObj login(String userName,String password){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UsernamePasswordToken(userName,password);
        try {
            subject.login(authenticationToken);
            return RequestObj.LOGIN_SUCESS;
        }catch (Exception e){
            return RequestObj.LOGIN_ERROR_PASS;
        }
    }
}
