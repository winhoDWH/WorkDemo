package com.dwh.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * @author: dengwenhao
 * @create: 2021-10-06
 **/
@Data
@Alias("Teacher")
public class Teacher {
    /**
     * 老师名
     */
    private String name;

    /**
     * 老师ID
     */
    private String tid;

    /**
     * 一个班主任有多个学生
     */
    private List<Student> students;
}
