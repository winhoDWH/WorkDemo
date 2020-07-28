package com.dwh.UserDemo;

public class LimitDouble {
    public static void main(String[] args){
        long a = 1000000;
        String b1 = "3.0";
        double b = Double.parseDouble(b1);

        /*//使用BigDecimal,固定显示小数点后几位
        BigDecimal bigDecimal = new BigDecimal((double) a/b);
        System.out.println(bigDecimal.setScale(4,BigDecimal.ROUND_HALF_UP).toString().length());
        System.out.println(bigDecimal.setScale(4,BigDecimal.ROUND_HALF_UP));
        */

        /*//不固定显示小数点后几位，即有小数才显示小数，可设置最大小数位
        double d = a/b;
        DecimalFormat df = new DecimalFormat("#.####");
        System.out.println(df.format(d));
        */

        //使用math.round()
        System.out.println((double) Math.round(3.455051*1000)/1000); //保留3位
    }
}
