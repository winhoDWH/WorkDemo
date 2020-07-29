package com.dwh.payweb.Config;

import com.dwh.payweb.entity.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro的配置类
 */
@Configuration
public class ShiroConfig {

    /**
    * shiroFilterFactoryBean
    */
    @Bean(name = "ShiroFilterFactory")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager SecurityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        filterFactoryBean.setSecurityManager(SecurityManager);
        //设置拦截器（过滤器）
        /*
            anon:无需认证就可以访问
            authc:必须认证了才能访问
            user:拥有对某个资源的权限才能访问
            perms:拥有某个角色权限才能使用
         */

        Map<String, String> stringFilterMap = new LinkedHashMap<>();
        stringFilterMap.put("/*","authc");
        filterFactoryBean.setFilterChainDefinitionMap(stringFilterMap);
        //当没有登录权限的时候就使用访问这个url
        filterFactoryBean.setLoginUrl("/tologin");
        return filterFactoryBean;
    }


    /**
     *创建securityMannager
     */
    @Bean(name = "SecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager= new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建realm对象，需要自定义类
     */
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
