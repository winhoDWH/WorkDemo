package com.dwh.spring.pojo;

public class StaticUserServiceImpl implements StaticUserService{
    @Override
    public void add() {
        System.out.println("新增");
    }

    @Override
    public void delete() {
        System.out.println("删除");
    }

    @Override
    public void edit() {
        System.out.println("修改");
    }

    @Override
    public void search() {
        System.out.println("查询");
    }
}
