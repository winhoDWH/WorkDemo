package com.dwh.springdemo.controller;

import com.dwh.springdemo.response.BaseResponse;
import com.google.gson.Gson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: dwh
 * @DATE: 2020/9/22
 */
@Api(tags = "Swagger测试用")
@RestController
@RequestMapping("Swagger")
public class SwaggerController {

    @ApiOperation(value = "测试swagger的方法一", notes = "备注说明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", defaultValue = "123"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, paramType = "header")
    })
    @RequestMapping("/Test")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功", response = BaseResponse.class),
            @ApiResponse(code = 400, message = "失败")
    })
    public String test(@RequestParam String password, @RequestHeader String age){
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", "0");
        map.put("message", "success");
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
