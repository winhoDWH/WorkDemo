package com.dwh.spring.config;

import com.dwh.spring.pojo.User;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Configuration：相当于将这个类标识为代替bean.xml文件去说明创建bean实例
 * @ComponentScan：是说明要去扫描的包
 * @Import(JavaConfig2.class)：引入另一个config类，相当于beans.xml中引入另一个xml文件
 */
@Configuration
@ComponentScan("com.dwh.spring.pojo")
@Import(JavaConfig2.class)
public class JavaConfig {

    /**
     * @bean：这个标签相当于xml文件中<bean/>标签，其id默认为方法名，也可以通过改标签的name或者value取别名
     * 1. name和value不能同时使用
     * 2. 使用name或者value之后不能在根据方法名获取bean实例
     */
    @Bean(value = {"user", "user1"})
    public User getUser(){
        return new User();
    }
}
