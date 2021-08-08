package com.dwh.patterns.chainPattern;

import com.dwh.patterns.chainPattern.impl.BossHandler;
import com.dwh.patterns.chainPattern.impl.DeptHandler;
import com.dwh.patterns.chainPattern.impl.GroupHandler;

/**
 * 责任链模式-客户端类
 * 需求：做一个处理员工请假和加薪的流程
 * 1. 有三种处理人：小组长、部门经理、总经理；
 * 2. 每种处理人权力不同：小组长和部门经理只能批假，只有总经理能处理加薪；
 * 3. 小组长只能批2天内的假，部门经理只能批5天内的假，总经理能批无限期的假
 * @author dengwenhao
 * date 2020-11-02
 */
public class Client {
    public static void main(String[] args) {
        BaseHandler handler1 = new GroupHandler("小明组长");
        BaseHandler handler2 = new DeptHandler("小明经理");
        BaseHandler handler3 = new BossHandler("小明总经理");

        /**配置组合**/
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        Request request = new Request();
        request.setDayCount(5);
        request.setMoney(0);
        request.setName("小红");
        request.setType(TypeEnum.DATE_TYPE);

        handler1.handleMessage(request);
    }
}
