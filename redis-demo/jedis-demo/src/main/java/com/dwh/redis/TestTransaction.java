package com.dwh.redis;

import org.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 测试redis事务处理
 * @author dengwenhao
 * data 2023-01-31
 */
public class TestTransaction {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.204.133", 6371);

        //清除数据库中数据
        jedis.flushDB();
        //jedis的操作必须要放在jedis.multi()方法执行前，不然会报错
        jedis.set("k1", "100");
        //监控
        //jedis.watch("k1");

        //获取到事务对象
        Transaction multi = jedis.multi();

        //监听，但是会在multi.exec();执行时才进行监听
        //未执行exec前，和其他待命指令一样
        //和jedis.watch("k1")是相同的作用（源码相同），但是代码运行时会有不同
        //比如下方代码中使用sleep让exec延后，睡眠期间修改k1的值并不能触发监听，因为multi.watch("k1")命令实际未提交执行
        //multi.watch("k1");
        try {
            //通过事务对象操作redis
            multi.decrBy("k1", 10);
            multi.set("k2", "v2");
            System.out.println("执行睡眠");
            //程序睡眠，只能测试jedis.watch("k1")，不能测试到multi.watch("k1")
            Thread.sleep(5000);
            //比如有代码运行失败
            //int i = 1/0;
            //解除监听
            //multi.unwatch();

            //执行事务
            multi.exec();
        }catch (Exception e){
            //如果捕获到程序有运行时异常后应该直接取消事务
            //使用watch如果有冲突也不会抛出异常
            System.out.println("报错，取消业务");
            multi.discard();
        }finally {
            //解除监听
            //jedis.unwatch();

            System.out.println(jedis.get("k1"));
            System.out.println(jedis.get("k2"));
            //关闭连接
            jedis.close();
        }
    }
}
