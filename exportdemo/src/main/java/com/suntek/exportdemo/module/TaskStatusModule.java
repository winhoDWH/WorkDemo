package com.suntek.exportdemo.module;

import cn.hutool.core.util.IdUtil;
import lombok.Getter;
import lombok.Setter;
import sun.rmi.runtime.Log;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 多线程任务状态类
 * @author: dwh
 * @DATE: 2020/9/25
 */
@Setter
@Getter
public class TaskStatusModule {
    /**总任务id**/
    private String taskId;
    /**失败任务信息集合**/
    private Map<String, Object> errorMap;
    /**总任务数**/
    private Long totalNum;
    /**成功任务数**/
    private AtomicLong successNum;
    /**失败任务数**/
    private AtomicLong failedNum;
    /**完成任务数**/
    private AtomicLong finishedNum;
    /**开始时间**/
    private Date startTime;
    /**结束时间**/
    private Date endTime;
    /**花费时间**/
    private Long useTimeMs;

    public TaskStatusModule(){
        taskId = IdUtil.simpleUUID();
        totalNum = 0L;
        successNum = new AtomicLong(0);
        failedNum = new AtomicLong(0);
        finishedNum = new AtomicLong(0);
        startTime = new Date();
    }

    public Long incrementSuccessNum(){
        return this.successNum.incrementAndGet();
    }

    public Long incrementFailedNum() {
        return this.failedNum.incrementAndGet();
    }

    public Long incrementFinishedNum() {
        return this.finishedNum.incrementAndGet();
    }

    public void putErrorMessage(String key, Object value){
        this.errorMap.put(key, value);
    }

    public void finish(){
        this.endTime = new Date();
        this.useTimeMs = this.endTime.getTime() - this.startTime.getTime();
    }
}
