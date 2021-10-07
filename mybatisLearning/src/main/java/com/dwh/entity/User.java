package com.dwh.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: dengwenhao
 * @create: 2021-10-05
 **/
@Data
public class User {
    private int id;

    private String userName;

    private String userCode;

    private Date createTime;

    private String phone;
}
