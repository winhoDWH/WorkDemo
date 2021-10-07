package com.dwh.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author: dengwenhao
 * @create: 2021-10-06
 **/
@Data
@Alias("Student")
public class Student {
    /**
     * 学生名字
     */
    private String name;

    /**
     * 学生ID
     */
    private String sid;

    /**
     *  一个学生对应一个班主任老师
     */
    private Teacher teacher;
}
