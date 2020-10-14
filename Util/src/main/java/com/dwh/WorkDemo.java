package com.dwh;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author: dwh
 * @DATE: 2020/10/14
 */
public class WorkDemo {
    public static void main(String[] args) throws Exception {
        RuleList list = parseBool("rule1|rule2&rule2");
        System.out.println(list.doProcess(2));
    }

    private static final Character AND = '&';
    private static final Character OR = '|';
    private static final Character RIGHT_BRACKET = '(';
    private static final Character LEFT_BRACKET = ')';
    List<RuleList> opt = new ArrayList<>();
    List<String> op = new ArrayList<>();

    public static RuleList parseBool(String str) throws Exception {
        int len = str.length();
        RuleList list = new RuleList();
        for (int i = 0; i < len; i++){
            Character s = str.charAt(i);
            if (AND.equals(s)){
                list.opt.add(AND);
            }else if (OR.equals(s)){
                list.opt.add(OR);
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
                list.rules.add(Rule.getOptionByName(stringBuffer.toString()));
            }
        }
        return list;
    }

    /**
     * 一个元素
     */
    static class RuleList{
        List<Option> rules = new ArrayList<>();
        List<Character> opt = new ArrayList<>();

        public boolean doProcess(int i){
            if (rules.size() == 1){
                return rules.get(0).optionMethod(i);
            }
            int len = opt.size();
            boolean res = rules.get(0).optionMethod(i);
            for (int n = 0; n < len ; n++){
                if (OR.equals(opt.get(n))){
                    if (!res){
                        res = rules.get(n + 1).optionMethod(i);
                    }
                }else {
                    if (res){
                        res = rules.get(n + 1).optionMethod(i);
                    }
                }
            }
            return res;
        }
    }

    /**
     * 规则对应方法类
     */
    static enum Rule{
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
        private Option option;

        Rule(String name, Option option) {
            this.name = name;
            this.option = option;
        }

        public static Option getOptionByName(String name) throws Exception {
            for (Rule r : Rule.values()){
                if (r.getName().equals(name)){
                    return r.getOption();
                }
            }
            throw new Exception("没有对应方法");
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Option getOption() {
            return option;
        }

        public void setOption(Option option) {
            this.option = option;
        }
    }

    interface Option{
        /**
         * method
         * @return
         */
        boolean optionMethod(int i);
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
