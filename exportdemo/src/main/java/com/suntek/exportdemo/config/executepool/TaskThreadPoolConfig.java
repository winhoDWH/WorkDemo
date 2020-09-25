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
    /**线程池*/
    private int corePoolSize;
    /**最大**/
    private int maxPoolSize;
    /****/
    private int keepAliveSeconds;
    /****/
    private int queueCapacity;
}
