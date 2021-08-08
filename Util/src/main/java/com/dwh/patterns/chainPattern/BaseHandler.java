package com.dwh.patterns.chainPattern;

/**
 * 抽象处理者
 * 注意是一个抽象类，用来定义变量
 * @author dengwenhao
 * date 2020-11-02
 */
public abstract class BaseHandler {
    protected BaseHandler nextHandler;
    protected String name;

    public BaseHandler(String name) {
        this.name = name;
    }

    /**
     * 请求处理类
     * @param request
     */
    public abstract void handleMessage(Request request);

    /**
     * 具体处理方法
     * 可以不统一定义在抽象类上,即直接在handleMessage中作定制化处理
     * @param request
     */
    protected abstract void echo(Request request);

    public void setNextHandler(BaseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public String getName() {
        return name;
    }
}
