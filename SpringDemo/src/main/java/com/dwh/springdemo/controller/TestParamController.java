package com.dwh.springdemo.controller;

import com.alibaba.fastjson.JSON;
import com.dwh.springdemo.request.TestRequest;
import com.dwh.springdemo.response.BaseResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * MIME类型传输
 * @author dengwenhao
 * date 2021-03-08
 */
@RestController
@RequestMapping("testParam")
public class TestParamController {

    /**
     *  application/x-www-from-urlencoded类型
     *  1. @RequestBody
     *  2. 不加标签的参数，可以的对应参数名匹配
     *  3. @RequestParam加上该标签，对应参数名也是可以匹配的
     */
    //@RequestMapping(value = "/FormUrlencoded", method = RequestMethod.POST)
    public void testFormUrlencoded(@RequestParam String name,@RequestParam String password){
        System.out.println(name);
        System.out.println(password);
        return;
    }

    @RequestMapping(value = "/FormUrlencoded", method = RequestMethod.POST)
    public void testFormUrlencoded(TestRequest request){
        System.out.println(JSON.toJSONString(request));
        return;
    }

    /**
     * multipart/form-data类型
     * 1. 参数上不添加标签时，没有默认值，基本数据类型（int,long这些）如果请求传入没有对应参数，则会报错；
     *      封装类（String，Long等）请求没有传入时赋值为null;
     * 2. 参数上加上@RequestParam可以控制校验请求是否传入某个参数
     * 3. 使用实体类去获取参数, 不用添加标签，@requestParam标签添加后会显示400错误，@RequestBody添加后显示415不支持该MIME类型
     * @return
     */
    //@RequestMapping(value = "testFromData", method = RequestMethod.POST)
    public BaseResponse testFormData(@RequestParam String name,@RequestParam(defaultValue = "0") Long password){
        System.out.println(name + password);
        return new BaseResponse("0", "success");
    }

    @RequestMapping(value = "testFromData", method = RequestMethod.POST)
    public BaseResponse testFormData(@RequestPart("file") MultipartFile file, @RequestParam String name,@RequestParam(defaultValue = "0") Long password){
        return new BaseResponse("0", "success");
    }

    @GetMapping("/testJson")
    public BaseResponse testJson(@RequestParam(required = false) String name,@RequestParam(required = false)  String password){
        System.out.println(name + password);
        return new BaseResponse("0", "");
    }

    //@PostMapping("/testJson")
    public BaseResponse testJson(TestRequest request){
        //System.out.println(name + password);
        return new BaseResponse("0", "");
    }

}
