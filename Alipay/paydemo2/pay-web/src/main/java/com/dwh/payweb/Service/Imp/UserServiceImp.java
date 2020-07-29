package com.dwh.payweb.Service.Imp;

import com.dwh.payweb.Service.UserService;
import com.dwh.payweb.entity.Business;
import com.dwh.payweb.mapper.BusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public Business getPassword(String id) {
        return businessMapper.selectByPrimaryKey(Integer.parseInt(id));
    }
}
