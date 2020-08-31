package com.dwh.date;

import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

/**
 * 格式化操作类
 * @author dwh
 */
public class FormatUtils {
    /**
     * 数字格式化
     * number<->String
     * parse()方法是：String->number
     * format()方法是：number->String
     */
    public static void numberFmt(){
        //中国，日本，美国的格式化器
        //德国的小数是带逗号的
        //中国日本货币用￥,美国用$
        Locale[] locales = new Locale[]{Locale.CHINA, Locale.JAPAN, Locale.US, Locale.GERMAN};

        NumberFormat[][] numbfmts = new NumberFormat[4][4];
        for (int i = 0; i < 4; i++){
            //货币格式化器
            numbfmts[i][0] = NumberFormat.getCurrencyInstance(locales[i]);
            //整数格式化器
            numbfmts[i][1] = NumberFormat.getIntegerInstance(locales[i]);
            //通用数值格式化器
            numbfmts[i][2] = NumberFormat.getNumberInstance(locales[i]);
            //百分比格式器
            numbfmts[i][3] = NumberFormat.getPercentInstance(locales[i]);
        }
        String[] desc = new String[]{"---中国格式---", "---日本---", "---美国---", "---德国---"};
        //只支持Long/double两种类型转换
        long currency = 22L;
        double number = 23.24;
        for (int i = 0; i < 4; i++){
            System.out.println(desc[i]);
            System.out.println("货币转换格式：" + numbfmts[i][0].format(currency));
            System.out.println("整数转换格式：" + numbfmts[i][1].format(number));
            System.out.println("通用数值转换格式：" + numbfmts[i][2].format(number));
            System.out.println("百分数转换格式：" + numbfmts[i][0].format(number));

        }
    }

    /**
     * 格式化时间
     */
    public static void BaseDateFmt(){
        //DateFormat是一个抽象类
        // 使用其构造方法获取的对象，只提供固定的时间格式转换
        //有四种静态常量FULL/LONG/MEDIUM/SHORT
        Locale[] locales = new Locale[]{Locale.CHINA, Locale.US};
        String[] localsName = new String[]{"---中国---", "---美国---"};
        String[] varName = new String[]{"FULL", "LONG", "MEDIUM", "SHORT"};
        DateFormat[][] dfms = new DateFormat[2][8];
        Date date = new Date();
        for (int i = 0; i < 2; i++){
            //只格式日期，有两个参数定制地区与格式
            dfms[i][0] = DateFormat.getDateInstance(DateFormat.FULL, locales[i]);
            dfms[i][1] = DateFormat.getDateInstance(DateFormat.LONG, locales[i]);
            dfms[i][2] = DateFormat.getDateInstance(DateFormat.MEDIUM, locales[i]);
            dfms[i][3] = DateFormat.getDateInstance(DateFormat.SHORT, locales[i]);
            //只格式时间，有两个参数定制地区与格式
            dfms[i][4] = DateFormat.getTimeInstance(DateFormat.FULL, locales[i]);
            dfms[i][5] = DateFormat.getTimeInstance(DateFormat.LONG, locales[i]);
            dfms[i][6] = DateFormat.getTimeInstance(DateFormat.MEDIUM, locales[i]);
            dfms[i][7] = DateFormat.getTimeInstance(DateFormat.SHORT, locales[i]);

            System.out.println(localsName[i]);
            System.out.println(varName[0] + "日期格式为：" + dfms[i][0].format(date));
            System.out.println(varName[1] + "日期格式为：" + dfms[i][1].format(date));
            System.out.println(varName[2] + "日期格式为：" + dfms[i][2].format(date));
            System.out.println(varName[3] + "日期格式为：" + dfms[i][3].format(date));
            System.out.println(varName[0] + "时间格式为：" + dfms[i][4].format(date));
            System.out.println(varName[1] + "时间格式为：" + dfms[i][5].format(date));
            System.out.println(varName[2] + "时间格式为：" + dfms[i][6].format(date));
            System.out.println(varName[3] + "时间格式为：" + dfms[i][7].format(date));
        }

        //自定义格式格式化时间
        //SimpleDateFormat类
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //DateTimeFormatter类
        //用来实现LocalTime等类的转换
        //3种获取DateTimeFormatter对象的方法
        DateTimeFormatter[] dateTimeFormatters = new DateTimeFormatter[]{
                //1. 直接使用其静态变量，就是一个DateTimeFormatter对象
                DateTimeFormatter.ISO_DATE_TIME,
                //2. 使用四种静态常量FULL/LONG/MEDIUM/SHORT创建
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL),
                //3. 使用自定义字符串创建
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        };
        //日期->字符串的方法：
        //1. 使用DateTimeFormatter.format()方法
        dateTimeFormatters[1].format(LocalDate.now());
        //2. 使用LocalDate类的format方法
        LocalDate.now().format(dateTimeFormatters[0]);

    }

    public static void main(String[] args) {
        //numberFmt();
        BaseDateFmt();
    }
}
