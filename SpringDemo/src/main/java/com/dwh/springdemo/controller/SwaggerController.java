package com.dwh.springdemo.controller;

import com.dwh.springdemo.response.BaseResponse;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * swagger标签演示类
 * @author: dwh
 * @DATE: 2020/9/22
 */
@Api(tags = "Swagger测试用")//Api标签用来标注该类是用swagger文档
@RestController
@RequestMapping("Swagger")
public class SwaggerController {

    /**ApiOperation标签用来说明这个方法使用swagger文档说明**/
    @ApiOperation(value = "演示swagger标签使用的方法一", notes = "备注说明")
    /**ApiImplicitParams用来说明请求中包含的参数**/
    @ApiImplicitParams({
            @ApiImplicitParam(name = "query", value = "query类型的参数", required = true, paramType = "query", dataType = "String" , defaultValue = "123"),
            @ApiImplicitParam(name = "header", value = "header类型的参数", required = true, paramType = "header"),
            @ApiImplicitParam(name = "path", value = "path类型的参数", required = true),
            @ApiImplicitParam(name = "body", value = "body类型的参数", required = true),
            @ApiImplicitParam(name = "form", value = "form类型的参数", required = true)
    })
    @RequestMapping(value = "/Test", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功", response = BaseResponse.class),
            @ApiResponse(code = 400, message = "失败")
    })
    public String test(@RequestParam String age){
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", "0");
        map.put("message", "success");
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
