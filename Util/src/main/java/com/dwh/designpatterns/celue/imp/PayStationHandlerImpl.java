package com.dwh.designpatterns.celue.imp;

import com.dwh.designpatterns.celue.BaseHandler;

import java.util.Map;

/**
 * @author: dwh
 * @DATE: 2020/10/16
 */
public class PayStationHandlerImpl implements BaseHandler {
    @Override
    public Object handler(Map<String, Object> map) {
        System.out.println("ps4 战神4真好玩");
        return null;
    }
}
