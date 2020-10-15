package com.dwh.work.rulesLink;
/**
 * 筛选方法接口
 * 其实现类主要实现具体过滤规则代码
 * @author dwh
 * @date 2020-10-15
 */
public interface RuleMethod {
    /**
     * 过滤数据规则
     * @param i
     * @return
     */
    boolean filterMethod(int i);
}
