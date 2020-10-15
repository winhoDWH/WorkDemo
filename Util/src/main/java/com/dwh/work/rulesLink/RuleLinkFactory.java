package com.dwh.work.rulesLink;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 工作中遇到
 * 需要通过写一个布尔表达式，解析该表达式达到通过配置获取特定规则判断数据集是否满足条件
 * 目的：满足通过简单配置可设定规则并方便增量开发
 * 优点：
 *  1. 可以直接解析简单布尔表达式
 *  2. 通过对枚举类的简单增量开发，使用lambda表达式即可制定一个新的规则
 * 缺点：
 *  1. 不支持括号情况
 * 规则链工厂类
 * @author: dwh
 * @DATE: 2020/10/14
 */
public class RuleLinkFactory {
    public static void main(String[] args) throws Exception {
        RuleLink list = createRuleLink("rule1|rule2&rule2");
        System.out.println(list.doProcess(2));
    }

    private static final Character AND = '&';
    private static final Character OR = '|';

    /**
     * 创建规则链
     * @param str
     * @return
     * @throws Exception
     */
    public static RuleLink createRuleLink(String str) throws Exception {
        int len = str.length();
        RuleLink list = new RuleLink();
        for (int i = 0; i < len; i++){
            Character s = str.charAt(i);
            //当遇到&和|两个符号时
            if (AND.equals(s) || OR.equals(s)){
               list.addOpt(s);
            }else {
                StringBuffer stringBuffer = new StringBuffer();
                Character next = s;
                while (!AND.equals(next) && !OR.equals(next)){
                    stringBuffer.append(next);
                    i++;
                    if (i < len){
                        next = str.charAt(i);
                    }else {
                        break;
                    }
                }
                i--;
                //新增规则
                list.addRule(RuleMethodEnum.getOptionByName(stringBuffer.toString()));
            }
        }
        return list;
    }

    public boolean parseBoolExpr(String expression) {

        int len = expression.length();
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < len; i++) {
            if (expression.charAt(i) == '(' || expression.charAt(i)==',') continue;
            else if (expression.charAt(i) == ')') {
                boolean mark_f = false, mark_t = false;
                while (!st.isEmpty() && (st.peek() == 't' || st.peek() == 'f')) {
                    if (st.peek() == 't') mark_t = true;
                    if (st.peek() == 'f') mark_f = true;
                    st.pop();
                }
                char c = st.pop();
                if (c == '!') {
                    if (mark_f) st.add('t');
                    else st.add('f');
                } else if (c == '&') {
                    if (mark_f) st.add('f');
                    else st.add('t');
                } else {
                    if (mark_t) st.add('t');
                    else st.add('f');
                }
            } else
                st.add(expression.charAt(i));
        }

        return st.peek() == 't' ? true : false;

    }


    public static JSONArray listToTree(JSONArray arr, String id, String pid, String child) {
        JSONArray r = new JSONArray();
        JSONObject hash = new JSONObject();
        //将数组转为Object的形式，key为数组中的id
        for (int i = 0; i < arr.size(); i++) {
            JSONObject json = (JSONObject) arr.get(i);
            hash.put(json.getString(id), json);
        }
        //遍历结果集
        for (int j = 0; j < arr.size(); j++) {
            //单条记录
            JSONObject aVal = (JSONObject) arr.get(j);
            //在hash中取出key为单条记录中pid的值
            JSONObject hashVP = (JSONObject) hash.get(aVal.get(pid).toString());
            //如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
            if (hashVP != null) {
                //检查是否有child属性
                if (hashVP.get(child) != null) {
                    JSONArray ch = (JSONArray) hashVP.get(child);
                    ch.add(aVal);
                    hashVP.put(child, ch);
                } else {
                    JSONArray ch = new JSONArray();
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }
            } else {
                r.add(aVal);
            }
        }
        return r;
    }

    /**
     * 按某一属性值给集合列表去重
     *
     * @param keyExtractor 自定义方法，获取用来比较的列表属性值
     */
    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
