package com.suntek.exportdemo.config;

import com.suntek.exportdemo.module.TaskStatusModule;

/**
 * 多线程任务状态管理类
 * @author: dwh
 * @DATE: 2020/9/25
 */
public class TaskStatusManager {
    private TaskStatusModule taskStatusModule;

    private TaskStatusManager(){
        taskStatusModule = new TaskStatusModule();
    }

    public TaskStatusModule getTaskStatusModule() {
        return taskStatusModule;
    }

    public static TaskStatusManager getInstance(){
        return Singleton.manager;
    }

    private static class Singleton{
        private static TaskStatusManager manager = new TaskStatusManager();
    }
}
