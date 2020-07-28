package com.dwh;

import com.alibaba.fastjson.JSONArray;

import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(",");
        sc.close();

        int n = str.length;
        int[] a = new int[n];
        for(int i = 0;i<n;i++){
            a[i] = Integer.parseInt(str[i]);
        }
        List<Integer> list = new LinkedList<>();
        list.add(a[0]-30);
        for(int i =1;i<n-1;i+=2){
            if((a[i]+90)<(a[i+1]-30)){
                list.add(a[i]+30);
                list.add(a[i+1]-30);
            }
        }
        list.add(a[n-1]+30);
        StringBuffer res = new StringBuffer();
        for(int i = 0;i<list.size();i++){
            res.append(list.get(i));
            if(i!=(list.size()-1)){
                res.append(",");
            }
        }
        System.out.println(res);
    }

}
