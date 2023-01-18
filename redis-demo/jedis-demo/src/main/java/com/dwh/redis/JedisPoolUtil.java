package com.dwh.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.*;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * 面向单机的redis
 * @author dengwenhao
 * data 2022-09-20
 */
public class JedisPoolUtil {

    private static final String HOST = "192.168.204.133";

    private static final Integer port = 6379;

    private static JedisPool pool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        //最大链接数
        config.setMaxTotal(10);
        //最大连接时间
        config.setMaxWait(Duration.ofMillis(10000));	//4.0.0-SNAPSHOT版本

        //config.setMaxWaitMillis(1000);
        pool = new JedisPool(config, HOST, port);
    }

    /**
     * 获取操作对象
     * @return
     */
    public static Jedis getConnect(){
        return pool.getResource();
    }

    public static void close(){
        pool.close();
    }

    /**
     * 连接多集群节点
     * 疑问一：GenericObjectPoolConfig<Connection>如何使用
     * 疑问二：线程不安全原理
     */
    private void connectCluster(){
        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        jedisClusterNode.add(new HostAndPort("192.168.1.31", 8001));
//		jedisClusterNode.add(new HostAndPort("192.168.1.31", 8002));
//		jedisClusterNode.add(new HostAndPort("192.168.1.31", 8003));
//		jedisClusterNode.add(new HostAndPort("192.168.1.31", 8004));
//		jedisClusterNode.add(new HostAndPort("192.168.1.31", 8005));
//		jedisClusterNode.add(new HostAndPort("192.168.1.31", 8006));
        int connectionTimeout = 3000;
        int soTimeout = 3000;
        int maxAttempts = 5;
        //自由分发对象池，方便当某个集群节点挂掉的时候自动匹配
        GenericObjectPoolConfig<Connection> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxIdle(100);
		poolConfig.setMaxWait(Duration.ofMillis(10000));	//4.0.0-SNAPSHOT版本
        //poolConfig.setMaxWaitMillis(10000);
        poolConfig.setTestOnBorrow(true);
        try (JedisCluster myCluster = new JedisCluster(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, poolConfig)) {
            //jedisCluster操作方法和redis命令差不多
            System.out.println(myCluster.getClusterNodes().keySet());
            int i = 0;
            while(i < 10) {
                String key1 = String.valueOf((char)('a' + i));
                System.out.println(key1 + " : " + myCluster.get(key1));
                String key2 = String.valueOf((char)('A' + i)) + "{hashtagX}";
                System.out.println(key2 + " -> " + myCluster.get(key2));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
            System.out.println(myCluster.mget("A{hashtagX}", "B{hashtagX}", "xxx{hashtagX}xxx"));	//批量查询key的slot需要一样
//			System.out.println(myCluster.mget("a", "b"));	//异常：No way to dispatch this command to Redis Cluster because keys have different slots.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
