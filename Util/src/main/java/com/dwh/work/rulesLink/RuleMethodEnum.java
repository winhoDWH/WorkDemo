package com.dwh.work.rulesLink;

import lombok.Data;

/**
 * 规则枚举类
 * 用来记录规矩命名和筛选方法实现之间关系
 * @author dwh
 * @date 2020-10-15
 */
public enum RuleMethodEnum {
    /**rule1**/
    RULE1("rule1", (n)->{
        System.out.println("rule1");
        return n > 2;
    }),
    /**rule2**/
    RULE2("rule2", (n)->{
        System.out.println("rule2");
        return n <= 2;
    })
    ;
    private String name;
    private RuleMethod option;

    RuleMethodEnum(String name, RuleMethod option) {
        this.name = name;
        this.option = option;
    }

    public String getName() {
        return name;
    }

    public RuleMethod getOption() {
        return option;
    }

    public static RuleMethod getOptionByName(String name) throws Exception {
        for (RuleMethodEnum r : RuleMethodEnum.values()){
            if (r.getName().equals(name)){
                return r.getOption();
            }
        }
        throw new Exception("没有对应方法");
    }
}
