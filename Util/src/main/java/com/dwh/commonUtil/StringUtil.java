package com.dwh.commonUtil;

/**
 * String的工具类
 * @author: dwh
 * @DATE: 2020/7/29
 */
public class StringUtil {
    /**
     * 当 obj 不为空时，转为字符串类型；为空时返回 ''
     * @param obj 要转换的对象
     * @return 转换后的字符串
     */
    public static String toString(Object obj)
    {
        return (obj == null?"":trim(obj.toString(),""));
    }

    /**
     * 若输入为字符为null,则输出forNull
     */
    public static String trim(String str, String forNull)
    {
        if(str==null) {
            return forNull;
        } else {
            return str.trim();
        }
    }

    public static void main(String[] args) {
        StringBuffer s = new StringBuffer();
        s.append("adkw233");
        System.out.println(s.toString());
        System.out.println(s.deleteCharAt(s.length() - 1).toString());
    }
}
