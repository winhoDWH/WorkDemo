package com.dwh.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: dengwenhao
 * @create: 2021-10-31
 **/
@Data
public class Blog {

    private String id;
    /**
     * 博客标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 浏览量
     */
    private int views;
}
