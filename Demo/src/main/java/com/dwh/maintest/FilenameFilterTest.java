package com.dwh.maintest;

import java.io.File;

/**
 * 文件过滤器
 * 演示如何使用list方法
 */
public class FilenameFilterTest {
    public static void main(String[] args){
        //以当前路径创建file对象
        File file = new File(".");
        //1. 文件后缀名为.java时
        //2. 或者名字对应一个路径，isDirectory()
        //满足以上任一条件被选出来
        String[] nameList = file.list((dir,name)->name.endsWith(".java")||new File(name).isDirectory());
        for (String name : nameList){
            System.out.println(name);
        }
    }
}
