package com.dwh.spring;

import com.dwh.spring.pojo.StaticUserService;
import com.dwh.spring.pojo.test.PersonImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ConcurrentHashMap;


public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

    }
}
