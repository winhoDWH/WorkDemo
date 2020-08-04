package com.dwh.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 基础JDBC连接工具类
 * @author: dwh
 * @DATE: 2020/8/4
 */
public class JdbcBaseCommon {
    /**JDBC连接参数**/
    private static String driver;
    private static String user;
    private static String password;
    private static String url;
    private static volatile JdbcBaseCommon jdbc;

    /**
     * 读取配置文件并初始化参数
     */
    private JdbcBaseCommon(){
        Properties properties = new Properties();
        ClassLoader classLoader = JdbcBaseCommon.class.getClassLoader();
        /*//定位文件的绝对路径
        URL res = classLoader.getResource("jdbc.properties");
        // 获取字符串路径
        String path = res.getPath();
        // 读取文件
        properties.load(new FileReader(path));*/
        InputStream io = classLoader.getResourceAsStream("jdbc.properties");
        try {
            //获取配置信息
            properties.load(io);
            driver = properties.getProperty("driver");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            Class.forName(driver);
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例获取
     */
    public static JdbcBaseCommon getInstance(){
        if (jdbc == null){
            synchronized (JdbcBaseCommon.class){
                if (jdbc == null){
                    jdbc = new JdbcBaseCommon();
                }
            }
        }
        return jdbc;
    }

    /**
     *  获取数据库连接
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 释放资源
     */
    public static void close(Statement stmt, Connection conn){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * @Author ZhaoPeiXuan
     * @Description 释放资源Pro版
     * @Date 22:13 2019/9/16
     * @Param [rs 结果集对象, stmt 执行sql的对象, conn 数据库连接对象]
     * @return void
     **/
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
