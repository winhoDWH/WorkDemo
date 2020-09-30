package com.suntek.exportdemo.config.executepool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 线程池配置属性类
 * @author: dwh
 * @DATE: 2020/9/25
 */
@Data
@ConfigurationProperties("thread.pool")
public class TaskThreadPoolConfig {
    /**核心线程池大小*/
    private int corePoolSize;
    /**最大线程数**/
    private int maxPoolSize;
    /**活跃时间**/
    private int keepAliveSeconds;
    /**队列容量**/
    private int queueCapacity;
}
