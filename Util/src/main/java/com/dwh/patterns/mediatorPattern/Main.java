package com.dwh.patterns.mediatorPattern;

/**
 * 中介者模式
 * 该例子是聊天室模型
 * 聊天室本身就是多个用户（实体）之间的中介者
 * 所以我们将分为两个实体类：用户以及聊天室
 * 将原本多个用户之间一对多的通信模式，修改为一个用户只需要对聊天室通信即可
 * @author: dengwenhao
 * @create: 2021-03-13
 **/
public class Main {

    public static void main(String[] args) {
        //实体1
        User user = new User("admin");
        user.sendMessage("admin用户发出一条信息");

        //实体2
        User user2 = new User("liming");
        user2.sendMessage("收到");
    }
}
