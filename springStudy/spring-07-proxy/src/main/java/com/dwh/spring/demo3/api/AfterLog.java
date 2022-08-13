package com.dwh.spring.demo3.api;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * 方法执行后切面
 * @author dengwenhao
 * data 2022-07-19
 */
public class AfterLog implements AfterReturningAdvice {

    /***
     *
     * @param returnValue 返回值
     * @param method 方法
     * @param objects 参数
     * @param o1 目标类
     * @throws Throwable
     */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("方法：" + method.getName() + "执行完，返回值为：" + returnValue);
    }
}
