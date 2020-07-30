package com.dwh.payweb.Controller;

import com.dwh.payweb.Response.UserList;
import com.dwh.payweb.Response.entity.PayList;
import com.dwh.payweb.Response.entity.PayResponse;
import com.dwh.payweb.Response.entity.UserResponse;
import com.dwh.payweb.Service.CheckPayService;
import com.dwh.payweb.Service.UserListService;
import com.dwh.payweb.mapper.PayMapper;
import com.dwh.payweb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/index")
public class indexController {
    @Autowired
    UserListService userListService;

    @Autowired
    PayMapper payMapper;

    @Autowired
    CheckPayService checkPayService;

    @RequestMapping("/userlist")
    public UserList getUserlist(){
        //List<UserResponse> data = userListService.getUserResponse();
        List<UserResponse> data = userListService.getUserResponse();
        return UserList.getRespon(data);
    }

    @RequestMapping("/test")
    public void test(){
    }

    @RequestMapping("/checkpay")
    public PayList getPayList(){
        return checkPayService.getPayList();
    }

}

