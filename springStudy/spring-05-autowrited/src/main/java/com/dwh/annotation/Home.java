package com.dwh.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;

import javax.annotation.Resource;

public class Home {
    @Autowired
    //指定某一个bean
    @Qualifier(value = "c1")
    private Child child;

    //@Autowired
    //与@Autowired类似，不过这个是java本身的注解
    @Resource
    private Father father;

    @Autowired(required = false)
    //允许为null
    //相当于@Nullable
    private Mother mother;

    public Child getChild() {
        return child;
    }

    public Father getFather() {
        return father;
    }

    public Mother getMother() {
        return mother;
    }

    @Override
    public String toString() {
        return "Home{" +
                "child=" + child +
                ", father=" + father +
                ", mother=" + mother +
                '}';
    }
}
