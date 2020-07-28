package com.dwh.Util;

import java.util.Objects;

/**
 * 缓存不可变类
 * 使用类似Integer的机制，节省创建对象的空间
 */
public class CacheImmutale {
    //缓存数组长度
    private  static int MAX_SIZE = 10;
    //创建缓存数组
    private static CacheImmutale[] cache = new CacheImmutale[MAX_SIZE];
    //设立指针，指向数组下一个要创建对象的位置
    private static int pos=0;
    private final String name;

    private CacheImmutale(String name){
        this.name= name;
    }

    public CacheImmutale valueof(String name){
        //遍历缓存数组
        for(int i = 0;i<MAX_SIZE;i++){
            //查看是否已有对象
            if(cache[i]!=null&&cache[i].getName().equals(name)){
                return cache[i];
            }
        }
        //如果数组满了，则将新的对象放入数组第一个
        if(pos==MAX_SIZE){
            cache[0]=new CacheImmutale(name);
            pos=1;
        }
        //如果没满就加入一个新的
        else{
            cache[pos++]=new CacheImmutale(name);
        }
        return cache[pos-1];
    }

    public String getName() {
        return name;
    }


    //重写equals和hashcode方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheImmutale that = (CacheImmutale) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        //return name.hashcode();
        return Objects.hash(name);
    }
}
