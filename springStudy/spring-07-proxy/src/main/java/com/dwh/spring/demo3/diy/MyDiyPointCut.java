package com.dwh.spring.demo3.diy;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * 使用自定义实现切面
 * @author dengwenhao
 * data 2022-07-19
 */
public class MyDiyPointCut {

    public void before(){
        System.out.println("======方法执行前=====");
    }

    public void after(){
        System.out.println("=====方法执行后======");
    }
}
