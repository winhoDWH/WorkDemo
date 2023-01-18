package com.dwh.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.LinkedList;

/**
 * @author dengwenhao
 * data 2022-09-20
 */
public class StringValueUtil {

    public static void ope(Jedis jedis){
        SetParams params = new SetParams();
        //设置存活时间,单位:秒
        params.ex(10000);
        //设置存活时间，单位：毫秒
        params.px(1000);
        //设置已存在不覆盖
        params.nx();
        //设置直接覆盖
        params.xx();

        //单值操作
        jedis.set("String_key1", "value");
        //设置参数
        //jedis.set("String_key1", "value", params);
        jedis.get("String_key1");
        jedis.setbit("String_key1", 0, true);
    }

}
