package com.dwh.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author: dwh
 * @DATE: 2020/9/16
 */
@Data
public class TestBean {
    private String name;
    private int age;
    /**
     * SerializedName可只支持多命名场景，一下三种取名都可被识别解析
     * gson 2.4版本以上就可以使用
     * 如果出现 {"emailAddress":"123", "email_Address" : "789", "email" : "456"}的情况
     * 则解析取email的值
     */
    @SerializedName(value = "emailAddress", alternate = {"email", "email_Address"})
    private String emailAddress;
    @SerializedName(value = "CivilCode")
    private String civilCode;
    /**
     * expose标签
     * 必须要与gsonBuilder连用
     * 连用的时候,GsonBuilder需要调用excludeFieldsWithoutExposeAnnotation()方法，实体类需要序列化的字段必须要加上注解
     * @Expose( deserialize = true,serialize = true) //序列化和反序列化都都生效，等价于上一条
     * @Expose( deserialize = true,serialize = false) //反序列化时生效
     * @Expose( deserialize = false,serialize = true) //序列化时生效
     * @Expose( deserialize = false,serialize = false) // 和不写注解一样
     */
    @Expose()
    private String parent;
}
