package com.dwh.payweb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/html")
    public String index(){

        System.out.println("访问成功");
        return "test";
    }

    @RequestMapping("/ftl")
    public String indexftl(){

        System.out.println("访问ftl成功");
        return "pay";
    }

   @RequestMapping("/tologin")
   public String tologin(){
       return "login";
   }

    @RequestMapping("/toindex")
    public String toindex(){
        return "index";
    }
    @RequestMapping("/toUserlist")
    public String toUserLIst(){return "Userlist";}
}
