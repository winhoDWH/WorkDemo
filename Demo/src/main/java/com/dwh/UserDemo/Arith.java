package com.dwh.UserDemo;


import java.math.BigDecimal;

/**
 * BigDecimal的工具类
 * 可以通过输入String或者Double类型数据直接获取运算结果
 * 默认除法精度为保留10位有效数字
 */
public class Arith {
    //默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
    //构造器私有，不让实例化
    private Arith(){};

    //加法运算
    public static double add(double v1 , double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.add(b2).doubleValue();
    }
    public static double add(String v1 , String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).doubleValue();
    }

    //减法运算
    public static double sub(double v1 , double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }
    public static double sub(String v1 , String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).doubleValue();
    }

    //乘法运算
    public static double mul(double v1 , double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.multiply(b2).doubleValue();
    }
    public static double mul(String v1 , String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).doubleValue();
    }

    //除法运算
    public static double div(double v1 , double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public static double div(String v1 , String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        //第一个参数是除数，第二个参数是保留第几位
        //第三个代表使用的模式
        //BigDecimal.ROUND_DOWN:直接省略多余的小数，比如1.28如果保留1位小数，得到的就是1.2
        //BigDecimal.ROUND_UP:直接进位，比如1.21如果保留1位小数，得到的就是1.3
        //BigDecimal.ROUND_HALF_UP:四舍五入，2.35保留1位，变成2.4
        //BigDecimal.ROUND_HALF_DOWN:四舍五入，2.35保留1位，变成2.3
        //后边两种的区别就是如果保留的位数的后一位如果正好是5的时候，一个舍弃掉，一个进位。
        return b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
