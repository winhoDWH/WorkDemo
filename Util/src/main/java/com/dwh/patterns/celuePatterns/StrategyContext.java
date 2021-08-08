package com.dwh.patterns.celuePatterns;

import java.util.Map;

/**
 * 策略模式的上下文
 * 我的理解是封装了调用策略算法的工具类
 * 对外提供统一方法进行调用
 * 外部使用策略算法的时候先生成该上下文然后传入具体策略算法实现类
 * @author: dwh
 * @DATE: 2020/10/16
 */
public class StrategyContext {
    private BaseHandler handler;

    public StrategyContext(BaseHandler handler) {
        this.handler = handler;
    }

    public void setHandler(BaseHandler handler) {
        this.handler = handler;
    }

    /**
     * 可以通过设定实例的成员变量进行策略方法的调用
     * @param map
     * @return
     */
    public Object query(Map<String, Object> map){
        return handler.handler(map);
    }

    /**
     * 也可以通过参数传入后调用
     * 两种方法都可以进行选择
     * @param map
     * @param handler
     * @return
     */
    public Object query(Map<String, Object> map, BaseHandler handler){
        return handler.handler(map);
    }
}
