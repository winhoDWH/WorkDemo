package com.dwh.commonUtil;

import java.io.Externalizable;
import java.io.Serializable;

public class TestEntity implements Serializable {
    private String name;

    public TestEntity(String name) {
        System.out.println("初始化");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
