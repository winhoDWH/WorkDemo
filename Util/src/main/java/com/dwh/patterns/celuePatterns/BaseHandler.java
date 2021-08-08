package com.dwh.patterns.celuePatterns;

import java.util.Map;

/**
 * 策略模式-策略算法接口
 * 也可以是抽象类，只要是能提供统一属性
 * @author: dwh
 * @DATE: 2020/10/16
 */
public interface BaseHandler {
    /**
     * 策略算法
     * @param map
     * @return
     */
    public Object handler(Map<String, Object> map);
}
