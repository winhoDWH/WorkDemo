package com.dwh.patterns.celuePatterns;

import com.dwh.patterns.celuePatterns.imp.PayStationHandlerImpl;

import java.util.HashMap;

/**
 * 策略模式-客户端模拟类
 * 即模拟调用策略模式
 * @author: dwh
 * @DATE: 2020/10/16
 */
public class StrategyMode {
    public static void main(String[] args) {
        StrategyContext context = new StrategyContext(new PayStationHandlerImpl());
        context.query(new HashMap<>());
        context.query(new HashMap<>(), new PayStationHandlerImpl());
    }
}