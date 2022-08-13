package com.dwh.spring.demo1.proxy;

import com.dwh.spring.pojo.StaticUserService;

public class StaticUserServiceProxy implements StaticUserService {

    private StaticUserService service;

    public void setService(StaticUserService service) {
        this.service = service;
    }

    @Override
    public void add() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void search() {

    }
}
