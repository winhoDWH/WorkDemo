package com.dwh.mapper;

import com.dwh.entity.Teacher;

import java.util.List;

/**
 * @author: dengwenhao
 * @create: 2021-10-07
 **/
public interface TeacherMapper {
    List<Teacher> selectAll();

    List<Teacher> selectAll2();
}
