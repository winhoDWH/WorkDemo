package com.dwh.work.rulesLink;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 规则链实体
 * 不包含括号的布尔表达式规则类
 * @author: dwh
 * @DATE: 2020/10/15
 */
public class RuleLink {
    private List<RuleMethod> rules = new ArrayList<>();
    private List<Character> opt = new ArrayList<>();

    private static final Character AND = '&';
    private static final Character OR = '|';

    /**
     * 执行规则链
     * 输入参数与筛选方法类RuleMethod的参数一致
     * @param i
     * @return
     */
    public boolean doProcess(int i){
        if (rules.size() == 1){
            return rules.get(0).filterMethod(i);
        }
        int len = opt.size();
        boolean res = rules.get(0).filterMethod(i);
        for (int n = 0; n < len ; n++){
            if (OR.equals(opt.get(n))){
                if (!res){
                    res = rules.get(n + 1).filterMethod(i);
                }
            }else {
                if (res){
                    res = rules.get(n + 1).filterMethod(i);
                }
            }
        }
        return res;
    }

    public void addRule(RuleMethod method) {
        rules.add(method);
    }

    public void addOpt(Character option) {
        opt.add(option);
    }
}
