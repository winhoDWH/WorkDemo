package com.dwh.mapper;

import com.dwh.entity.User;

import java.util.List;

/**
 * @author: dengwenhao
 * @create: 2021-10-05
 **/
public interface UserMapper {
    List<User> selectOne();

    int insertOne(User user);
}
