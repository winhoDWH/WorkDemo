package com.dwh.mapper;

import com.dwh.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {

    /**
     * 编写where语句
     * @param title
     * @param author
     * @param sort
     * @return
     */
    List<Blog> selectByWhere(@Param("title")String title, @Param("author")String author, @Param("sort")String sort);

    int updateById(Blog blog);
}
