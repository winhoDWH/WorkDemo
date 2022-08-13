package com.dwh.spring.demo2.Util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 自定义代理类调用过程
 */
public class MyInvocationHandler implements InvocationHandler {

    private final Object proxyObj;

    public MyInvocationHandler(Object proxyObj) {
        this.proxyObj = proxyObj;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logBefore(method.getName());
        System.out.println(proxy.getClass());
        Object value = method.invoke(proxyObj, args);
        logAfter();
        return value;
    }

    private void logBefore(String methodName){
        System.out.println("执行操作名为：" + methodName);
    }

    private void logAfter(){
        System.out.println("操作执行完毕");
    }
}
