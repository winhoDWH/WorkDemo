package com.dwh.spring.demo3.api;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 方法执行前执行切面
 * @author dengwenhao
 * data 2022-07-19
 */
public class Log implements MethodBeforeAdvice {

    /**
     *
     * @param method 要执行目标对象方法
     * @param objects 参数
     * @param o 目标对象
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("方法：" + method.getName() + "执行开始");


    }
}
