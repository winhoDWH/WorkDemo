package com.dwh.patterns.mediatorPattern;

import lombok.ToString;

/**
 * 聊天室类
 * 中介者类，提供一个静态方法，提供给实体类，进行实体类的通信或动作
 * @author: dengwenhao
 * @create: 2021-03-13
 **/
@ToString
public class MediatorModel {
    public static void mediatorHandle(User user, String context){
        System.out.println("用户：[" + user.getName() + "]发言: " + context);
    }
}
