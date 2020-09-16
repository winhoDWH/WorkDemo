package com.dwh.gson;

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
}
