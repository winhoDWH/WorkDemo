package com.dwh.controller;

import io.swagger.models.Xml;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ProvincePrice")
public class ProvincePriceController {

    @RequestMapping(value = "/provincePrice")
    public void price(@RequestBody String json){
        //解析数据，拼成接口信息

        //排序

        //调用路径拟合服务

        //区分拟合路径新增节点

        //获取返回路径，然后计费
    }
}
