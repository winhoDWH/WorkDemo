package com.dwh.spring.pojo.test;

/**
 * @author dengwenhao
 * data 2022-08-02
 */
public abstract class Man extends Person{

    @Override
    public void jump() {
        System.out.println("man jump");
    }

    @Override
    public void eat() {
        System.out.println("man 吃（共有）");
    }

    public abstract void say();
}
