package com.dwh.mapper;

import com.dwh.entity.Student;

import java.util.List;

/**
 * @author: dengwenhao
 * @create: 2021-10-07
 **/
public interface StudentMapper {
    List<Student> getStudent();

    /**
     * 模拟复合外键情况
     * @return
     */
    List<Student> getStudent2();

    List<Student> getStudentJoin();
}
