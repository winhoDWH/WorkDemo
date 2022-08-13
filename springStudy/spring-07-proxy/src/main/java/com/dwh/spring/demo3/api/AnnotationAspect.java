package com.dwh.spring.demo3.api;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author dengwenhao
 * data 2022-08-02
 */
@Component
@Aspect
public class AnnotationAspect {
    @Pointcut("execution(* com.dwh.spring..*(..)) && this(com.dwh.spring.pojo.test.Person)")
    private void pc(){}

    @Pointcut("execution(* com.dwh.spring..*(..)) && target(com.dwh.spring.pojo.test.Person)")
    private void pc2(){}

    @Before("pc()")
    public void exeThis(){
        System.out.println("匹配this");
    }

    @Before("pc()")
    public void exeTarget(){
        System.out.println("匹配target");
    }
}
