package com.dwh.redis;

import redis.clients.jedis.Jedis;

/**
 * @author dengwenhao
 * data 2022-09-20
 */
public class TestApplication {

    public static void main(String[] args) {
        //StringValueUtil.ope(JedisPoolUtil.getConnect());
        Jedis jedis = new Jedis("192.168.204.133", 6379);
        StringValueUtil.ope(jedis);
        jedis.close();
    }
}
