package com.dwh;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * 读取配置文件工具类
 * @author: dwh
 * @DATE: 2020/9/9
 */
public class ReadPropertiesUtil {
    /**
     * 使用properties类去读取配置文件
     */
    public static void baseMethod() throws Exception {
        Properties properties = new Properties();
        //IDEA将项目根目录当做类的相对路径，本项目的根目录在WorkDemo这个目录上
        properties.load(new FileInputStream("Util/src/main/resources/Test.properties"));
        //会被打印出来，但不会改变配置文件信息
        properties.setProperty("password", "123");
        System.out.println(properties);
    }

    public static void main(String[] args) throws Exception {
        baseMethod();
    }
}
