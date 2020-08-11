package com.dwh.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * FastJson的工具类
 * JSON是一种特殊的字符串格式，{name:dwh};(map的字符串格式是：{name=dwh})
 * @author: dwh
 * @DATE: 2020/8/11
 */
public class FastJsonUtils {
    /**
     * json常见操作
     */
    private void commentUseMethod(){
        /**Json序列化一个javabean成JSON字符串**/
        String objectToJsonString = JSON.toJSONString(new Person("dwh", 21, 1, new Date()));
        Map<String, Object> map = new HashMap<>(2);
        /**将map转成json字符串**/
        String mapToJsonString = JSON.toJSONString(map);
        /** 将JSON字符串转成指定对象，如果不指定，则转成JSONObject对象 **/
        /** 指定类必须要有无参数的构造函数 **/
        Person jsonStringToObject = JSON.parseObject(objectToJsonString, Person.class);
        /**JSON字符串转JSONObject**/
        JSONObject stringToJsonObject = JSON.parseObject(objectToJsonString);
        JSONObject.parseObject(objectToJsonString);
    }

    /**
     * 用来被json序列化的对象
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class Person{
        /**
         * 该标签可加可不加，对象都可以被序列化
         * 加了标签之后可以修改序列化后的命名等
         * name属性：起别名；   serialize属性：false指定字段不序列化；
         * ordinal属性：规定字段序列化后的顺序；（默认的序列化顺序是根据name的字母顺序）
         * format属性：用于格式化date属性
         * 如果属性是私有的，就必须有set方法，否则无法反序列化
         * 必须要有无参数的构造器，不然反序列化会报错：JSONException: default constructor not found.
         */
        @JSONField(name = "Name")
        private String name;
        private int age;
        private int sex;
        private Date birthday;
    }
}
