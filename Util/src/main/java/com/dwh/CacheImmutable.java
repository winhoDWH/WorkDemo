package com.dwh;

import java.util.Objects;

/**
 * 缓存不可变类
 * 使用类似Integer的机制，节省创建对象的空间
 * 只能通过某一个属性的值获取对应的类的实例
 * @author dwh
 */
public class CacheImmutable {
    /**缓存数组长度**/
    private static int MAX_SIZE = 10;
    /**创建缓存数组*/
    private static CacheImmutable[] cache = new CacheImmutable[MAX_SIZE];
    /**设立指针，指向数组下一个要创建对象的位置*/
    private static int pos = 0;

    private final String name;

    private CacheImmutable(String name) {
        this.name = name;
    }

    /**
     * 根据类的属性值获取类的实例
     */
    public CacheImmutable valueOf(String name) {
        //遍历缓存数组
        for (int i = 0; i < MAX_SIZE; i++) {
            //查看是否已有对象
            if (cache[i] != null && cache[i].getName().equals(name)) {
                return cache[i];
            }
        }
        //如果数组满了，则将新的对象放入数组第一个
        if (pos == MAX_SIZE) {
            cache[0] = new CacheImmutable(name);
            pos = 1;
        }
        //如果没满就加入一个新的
        else {
            cache[pos++] = new CacheImmutable(name);
        }
        return cache[pos - 1];
    }

    public String getName() {
        return name;
    }


    /**
     * 重写equals和hashcode方法
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheImmutable that = (CacheImmutable) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
