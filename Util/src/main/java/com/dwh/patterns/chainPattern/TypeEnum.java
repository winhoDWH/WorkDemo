package com.dwh.patterns.chainPattern;

/**
 * 请求类型枚举类
 * @author dengwenhao
 * date 2020-11-02
 */
public enum TypeEnum {
    /**
     * 加薪请求
     */
    MONEY_TYPE("加薪"),
    /**
     * 请假请求
     */
    DATE_TYPE("请假");
    private String type;

    TypeEnum(String type) {
        this.type = type;
    }
}
