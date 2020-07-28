package com.dwh.payweb.Service.Imp;

import com.dwh.payweb.Response.entity.UserResponse;
import com.dwh.payweb.Service.UserListService;
import com.dwh.payweb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserListServiceImp implements UserListService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserResponse> getUserResponse() {
        return userMapper.selectall();
    }
}
