package com.dwh.spring.pojo.test;

import org.springframework.stereotype.Component;

/**
 * @author dengwenhao
 * data 2022-08-02
 */
@Component("personImpl")
public class PersonImpl extends Man{
    @Override
    public void say() {
        System.out.println("persomImpl: say(man 独有)");
    }
}
