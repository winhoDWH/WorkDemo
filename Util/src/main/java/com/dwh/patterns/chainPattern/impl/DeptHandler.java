package com.dwh.patterns.chainPattern.impl;

import com.dwh.patterns.chainPattern.BaseHandler;
import com.dwh.patterns.chainPattern.Request;
import com.dwh.patterns.chainPattern.TypeEnum;

/**
 * 部门经理类
 * 1. 只能批五天内的假
 * 2. 不能处理加薪请求
 * @author dengwenhao
 * date 2020-11-02
 */
public class DeptHandler extends BaseHandler {
    private static final Integer ALLOW_DAYS = 5;

    public DeptHandler(String name) {
        super(name);
    }

    @Override
    public void handleMessage(Request request) {
        if (TypeEnum.DATE_TYPE.equals(request.getType()) && ALLOW_DAYS.compareTo(request.getDayCount())>=0){
            echo(request);
        }else{
            System.out.println("部门经理：" + this.getName() + "无权批该请求，向上转发请求");
            this.nextHandler.handleMessage(request);
        }
    }

    @Override
    protected void echo(Request request) {
        System.out.println(this.getName() + "批准员工" + request.getName() + "请假，批准假期天数为：" + request.getDayCount());
    }
}
