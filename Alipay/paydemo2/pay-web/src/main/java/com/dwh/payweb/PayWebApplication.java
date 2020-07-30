package com.dwh.payweb;

import com.dwh.payweb.entity.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan({"com.dwh.payweb.mapper"})
public class PayWebApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(PayWebApplication.class, args);
        /*DefaultWebSecurityManager manager= (DefaultWebSecurityManager) run.getBean("SecurityManager");
        UserRealm realm = (UserRealm) run.getBean("userRealm");
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println(manager.getRealms());
        System.out.println(realm);
        ShiroFilterFactoryBean filter = (ShiroFilterFactoryBean) run.getBean("ShiroFilterFactory");
       // System.out.println(run.getBean("ShiroFilterFactory").getClass());
        System.out.println(manager);
        System.out.println(filter.getSecurityManager());*/
    }

}
