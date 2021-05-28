package com.dwh.springdemo.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengwenhao
 * date 2021-05-28
 */
@RestController
@RequestMapping("nacos")
public class TestNacosController {

    @NacosValue(value = "${useConfig:false}", autoRefreshed = true)
    private String nacosValue;

    @NacosValue(value = "${otherConfig:f}", autoRefreshed = true)
    private String nacosValue2;

    @Value(value = "${dwh.copy:test}")
    private String testConfig;

    @GetMapping("config")
    public String config(){
        return nacosValue + "+" + nacosValue2 + "+" + testConfig;
    }
}
