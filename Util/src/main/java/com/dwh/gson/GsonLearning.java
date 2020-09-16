package com.dwh.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * gson学习
 * @author: dwh
 * @DATE: 2020/9/16
 */
public class GsonLearning {
    public static void main(String[] args) {

    }

    private static void test(){
        Gson gson = new Gson();
        /**
         * 只是简单转成字符串
         */
        String jsonNumber = gson.toJson(100);
        String jsonBoolean = gson.toJson(false);
        String jsonString = gson.toJson("String");
        System.out.println(jsonNumber);
        System.out.println(jsonBoolean);
        System.out.println(jsonString);
        /**
         * 泛型处理
         * 使用TypeToken
         * 可用来解决接口泛型问题
         */
        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
        String jsonResponse = "{......}";
        List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>(){}.getType());
        //解析一个data为string类型的请求
        Request<String> requestStr = gson.fromJson(jsonResponse, new TypeToken<String>(){}.getType());
        //解析一个data为List<String>类型的请求
        Request<String> requestList = gson.fromJson(jsonResponse, new TypeToken<List<String>>(){}.getType());
    }

    /**
     * 测试gsonBuilder类
     */
    private static void testGsonBuilder(){
        Gson gson = new GsonBuilder()
                //输出类中值为Null的属性到json串中
                .serializeNulls()
                .create();
    }
}
