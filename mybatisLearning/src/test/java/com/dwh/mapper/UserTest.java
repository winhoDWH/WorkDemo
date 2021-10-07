package com.dwh.mapper;

import com.alibaba.fastjson.JSON;
import com.dwh.entity.User;
import com.dwh.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;

/**
 * @author: dengwenhao
 * @create: 2021-10-06
 **/
public class UserTest {

    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtil.getSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(userMapper.selectOne());
        //关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = MybatisUtil.getSession();
        User user = new User();
        user.setCreateTime(new Date());
        user.setPhone("123456");
        user.setUserCode("1245");
        user.setUserName("小明");
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.insertOne(user);

        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelect(){
        SqlSession sqlSession = MybatisUtil.getSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        System.out.println(JSON.toJSONString(studentMapper.getStudent()));
        //关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void testSelectJoin(){
        SqlSession sqlSession = MybatisUtil.getSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        System.out.println(JSON.toJSONString(studentMapper.getStudentJoin()));
        //关闭sqlSession
        sqlSession.close();
    }
}
