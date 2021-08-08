package com.dwh.patterns.chainPattern.impl;

import com.alibaba.fastjson.JSON;
import com.dwh.patterns.chainPattern.BaseHandler;
import com.dwh.patterns.chainPattern.Request;

/**
 * 总经理类
 * 可以批任何假期和处理加薪
 * 思考：该场景下，该类确定是链的结束，所以不调用下一个handler就不会有空指针问题
 * 如果是随意组合的情况，则需要先判断下一个handler是否为空再进行调用
 * @author dengwenhao
 * date 2020-11-02
 */
public class BossHandler extends BaseHandler {
    public BossHandler(String name) {
        super(name);
    }

    @Override
    public void handleMessage(Request request) {
        echo(request);
    }

    @Override
    protected void echo(Request request) {
        System.out.println(JSON.toJSONString(request));
    }
}
