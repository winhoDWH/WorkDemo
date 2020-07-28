package com.dwh.maintest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dwh.UserDemo.HttpRequestUtils;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import javafx.util.Pair;
import org.dom4j.DocumentException;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class test {

    public static void main(String[] args) throws ParseException {
        /*List<Integer> s = radoms(20);
        System.out.println(s.toString());
        int worry = 0;
        int j = 0;
        List<String> list = new ArrayList<>();
        boolean frontkey = false, afterkey = false;
        int frontnumber = 0, afternumber = 0;
        for (int i = 0; i < s.size(); i++) {
            int key;
            //拟合数据
            if (s.get(i) != 1) {
                if (frontkey == false) {
                    frontkey = true;
                    frontnumber += 1;
                } else {
                    if (afterkey == false) {
                        frontnumber += 1;
                    } else afternumber += 1;
                }
            } else {
                //非拟合数据
                if (frontkey == true) {
                    if (frontnumber < 3) {
                        frontkey = false;
                        frontnumber = 0;
                    } else {
                        if (afterkey == false) {
                            afterkey = true;
                            worry = i;
                        } else {
                            if (afternumber >= 3) {
                                list.add(String.valueOf(worry));
                            }
                            frontnumber = afternumber;
                            worry = i;
                            afternumber = 0;
                        }
                    }
                }
            }
        }
        System.out.println(list.toString());*/
       /* JSONObject jsonObject = new JSONObject();
        jsonObject.put("hhh","aaa");
        System.out.println(jsonObject.toString());*/

    }

    public static List<Integer> radoms(int n) {
        List<Integer> list = new ArrayList<>();
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < n; i++) {
            list.add(random.nextInt(2));
        }
        return list;
    }

}



