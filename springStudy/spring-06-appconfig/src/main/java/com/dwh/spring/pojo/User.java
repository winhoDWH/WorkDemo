package com.dwh.spring.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;

public class User {

    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    @Value("hhhh")
    public void setName(String name) {
        this.name = name;
    }
}
