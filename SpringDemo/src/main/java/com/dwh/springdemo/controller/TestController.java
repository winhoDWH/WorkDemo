package com.dwh.springdemo.controller;

import com.dwh.springdemo.response.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengwenhao
 * date 2020-11-02
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public BaseResponse testPost(){
        return new BaseResponse("1", "success");
    }
}
