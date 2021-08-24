package com.dwh.cache;

import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

/**
 * LoadingCache缓存架构
 * @author dengwenhao
 * date 2021-08-24
 */
public class LoadingCacheUtil {

    //https://www.jianshu.com/p/3d546868a1db
    //https://www.cnblogs.com/rickiyang/p/11074159.html
    public static LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            //设置缓存最大存储容量,当缓存项接近该大小时，开始回收旧的缓存项
            .maximumSize(1000)
            //设置多少时间后对象没有被读/写访问则从内存缓存中删除
            .expireAfterAccess(10, TimeUnit.MINUTES)
            //移除监听器，当有元素被移除出缓存时触发
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> rn) {
                    System.out.println("移除了" + rn.getKey() + "缓存项");
                }
            })
            //自身的统计功能
            .recordStats()
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    //处理查询缓存键不存在内存时的逻辑，即：加载数据的方式，如从mysql读
                    if ("1".equals(s)){
                        return "缓存一";
                    }
                    return "剩余缓存";
                }
            });

    public void test(){

    }
}
