package com.dwh.gson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Target;
import java.util.List;

/**
 * gson学习
 * @author: dwh
 * @DATE: 2020/9/16
 */
public class GsonLearning {
    public static void main(String[] args) {
        Request<TestBean> request = new Request<>();
        request.setCode("0");
        request.setMessage("success");
        TestBean bean = new TestBean();
        bean.setAge(20);
        bean.setCivilCode("4401");
        bean.setEmailAddress("123456@163.com");
        bean.setName("dwh");
        request.setData(bean);
        Gson gson = new Gson();
        System.out.println(gson.toJson(request));
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
                //设置日期时间格式，另有两个重载方法
                //在序列化和反序列化时均生效
                //另外两个重载方法输入整数为参数，整数对应FULL,LONG等规定好的格式
                .setDateFormat("yyyy-MM-dd")
                //不序列化内部类
                .disableInnerClassSerialization()
                //不序列化html标签
                .disableHtmlEscaping()
                //格式化输出，什么意思？
                .setPrettyPrinting()
                .create();
    }
}
