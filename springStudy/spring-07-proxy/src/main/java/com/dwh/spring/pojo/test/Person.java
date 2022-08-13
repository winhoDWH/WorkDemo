package com.dwh.spring.pojo.test;

/**
 * @author dengwenhao
 * data 2022-08-02
 */
public abstract class Person {

    public void run(){
        System.out.println("person 奔跑");
    }

    public void eat(){
        System.out.println("person 吃（共有）");
    }

    public abstract void jump();
}
