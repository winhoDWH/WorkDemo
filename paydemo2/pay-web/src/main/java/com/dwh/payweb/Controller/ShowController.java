package com.dwh.payweb.Controller;

import com.dwh.payweb.Service.ShowGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 显示数据
 */
@Controller
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowGoodsService showGoodsService;

    /**
     * 显示数据库数据
     * @return
     */
    @RequestMapping("/data")
    @ResponseBody
    public Object data(ModelMap modelMap, HttpServletRequest request){
        Map map = showGoodsService.showdata();
        return map;
    }
}
