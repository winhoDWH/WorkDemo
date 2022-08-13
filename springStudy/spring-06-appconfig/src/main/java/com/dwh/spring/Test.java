package com.dwh.spring;

import com.dwh.spring.config.JavaConfig;
import com.dwh.spring.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Primary;


/**
 * 这里AnnotationConfigApplicationContext就是通过java类替代了xml文件书写bean实例配置的application实现
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);

        User user = (User) applicationContext.getBean("user1");
        System.out.println(user);
    }
}
