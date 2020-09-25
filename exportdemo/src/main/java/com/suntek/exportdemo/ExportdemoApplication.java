package com.suntek.exportdemo;

import com.suntek.exportdemo.config.executepool.TaskThreadPoolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 启动类
 * @author dwh
 */
@SpringBootApplication
@EnableConfigurationProperties({TaskThreadPoolConfig.class})
public class ExportdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExportdemoApplication.class, args);
    }

}
