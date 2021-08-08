package com.dwh.patterns.mediatorPattern;

import lombok.Data;

/**
 * @author: dengwenhao
 * @create: 2021-03-13
 **/
public class User {
    /**
     * 姓名
     */
    private String name;
    /**
     * 真实情况下还有别的属性，如：聊天等级、登录密码等
     */

    public void sendMessage(String message){
        MediatorModel.mediatorHandle(this, message);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }
}
