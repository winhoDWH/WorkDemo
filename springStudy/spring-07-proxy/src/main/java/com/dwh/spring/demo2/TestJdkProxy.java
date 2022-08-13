package com.dwh.spring.demo2;

import com.dwh.spring.pojo.StaticUserService;
import com.dwh.spring.pojo.StaticUserServiceImpl;
import com.dwh.spring.demo2.Util.MyInvocationHandler;

import java.lang.reflect.Proxy;

public class TestJdkProxy {
    public static void main(String[] args) {
        MyInvocationHandler myInv = new MyInvocationHandler(new StaticUserServiceImpl());
        //生成代理类
        StaticUserService o = (StaticUserService) Proxy.newProxyInstance(TestJdkProxy.class.getClassLoader(), new Class[]{StaticUserService.class}, myInv);
        o.add();
        o.delete();
        o.edit();
        o.search();
    }
}
