package com.dwh.date;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

/**
 * java日期基础
 * @author: dwh
 * @DATE: 2020/8/25
 */
@Slf4j
public class DateBase {

    /**
     * date类的一些基本方法
     */
    public static void dateOption(){
        Date date1 = new Date();
        //比当前时间多1000ms的时间
        Date date2 = new Date(System.currentTimeMillis() + 1000);
        //判断两时间谁前谁后
        System.out.println(date1.after(date2));
        System.out.println(date1.before(date2));
        //获取date比GMT 1970-01-01 00:00:00的时差
        System.out.println(date1.getTime());
        System.out.println(date1);
        date1.setTime(System.currentTimeMillis());
        System.out.println(date1);
    }

    /**
     * Calendar类：date的操作类
     */
    public static void calendarOption(){
        //获取Calendar实例
        Calendar calendar = Calendar.getInstance();
        //由Calendar获取date实例
        Date date1 = calendar.getTime();
        //由date设置Calendar
        Date date2 = new Date();
        calendar.setTime(date2);
        /**
         * 操作date的过程：使用Calendar类的实例方法
         */
        System.out.println(calendar.getTime());
        //修改日期，两个参数都是Int类型，第一个参数是指改变的时间单位（月、年等），Calendar有静态变量可用
        calendar.add(Calendar.YEAR, 1);
        System.out.println(calendar.getTime());
        //get()方法，同样需要传时间单位
        System.out.println("修改后此时年的数值为：" + calendar.get(Calendar.YEAR));
        //返回某时间单位的最大最小值
        System.out.println("月的最大值：" + calendar.getActualMaximum(Calendar.MONTH));
        System.out.println("月的最小值：" + calendar.getActualMinimum(Calendar.MONTH));
        //设置固定日期,注意月份是从0开始，所以这里设成4之后，打印出来的是5月
        calendar.set(Calendar.MONTH, 4);
        System.out.println(calendar.getTime());
        //两种set的用法，下面这种是直接一起设置年月日，也有一起设置年月日时分秒
        calendar.set(2017, 5, 30);
        System.out.println(calendar.getTime());
        Date date = calendar.getTime();
        //新增8个月
        calendar.add(Calendar.MONTH, 8);
        System.out.println("使用add" + calendar.getTime());
        //减少8个月
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -4);
        System.out.println(calendar.getTime());
        calendar.setTime(date);
        calendar.roll(Calendar.MONTH, 8);
        System.out.println("使用roll"+calendar.getTime());
        calendar.roll(Calendar.MONTH, -4);
        System.out.println("使用roll"+calendar.getTime());
        //容错性
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 13);
        System.out.println("未关闭容错性" + calendar.getTime());
        calendar.setLenient(false);
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 13);
        System.out.println("关闭容错性");
        //上一句是可以被打印出来的，即使此时set方法应该使用错误
        //这说明了set的运算是有延迟性的
        System.out.println(calendar.getTime());
    }

    /**
     * LocalDate、LocalTime、LocalDateTime三种时间类
     */
    public static void newAddDate(){
        /**
         * LocalDate 不带时分秒的日期 即：2020-01-17
         */
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        log.debug(localDate.toString());

        /**
         * LocalTime 只有时分秒的时间 即16:23:25
         */
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        /**
         * LocalDateTime 上面两个组合起来
         */
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

    }

    public static void main(String[] args) {
        //dateOption();
        calendarOption();
    }
}
