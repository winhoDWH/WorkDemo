package com.dwh.javaBase;

/**
 * 内部类
 *
 * @author: dwh
 * @DATE: 2020/9/3
 */
public class InnerClass {
    /**
     * 看内部类访问私有变量，实例变量， 静态变量
     **/
    private int size = 10;
    int height = 50;
    static int width = 20;

    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();
        System.out.println("---非静态内部类---");
        //非静态内部类
        innerClass.testNonStaticMethod();
        System.out.println("---静态内部类---");
        //静态内部类，实际上可以直接调用
        innerClass.testStaticMethod();
        System.out.println("---匿名内部类---");
        String s= "a";
        innerClass.testNonName(new Person() {
            @Override
            void test() {
                //使用外部类成员
                System.out.println(s);
                //不能改变值，编译报错，下面语句报错
                //s = "1";
            }
        });
        innerClass.testNonName(new Person(s, "使用有参方法") {

            @Override
            void test() {
            }
        });
    }

    /**
     * 测试非静态内部类
     */
    public void testNonStaticMethod() {
        NonStaticClass nc = new NonStaticClass();
        nc.test(2);
    }

    /**
     * 测试静态内部类
     */
    public void testStaticMethod() {
        //访问其静态和非静态成员
        System.out.println(StaticClass.name);
        System.out.println(new StaticClass().sex);
    }

    public void testNonName(Person p) {
        System.out.println(p.sex);
        System.out.println(p.name);
    }

    /**
     * 非静态内部类
     */
    class NonStaticClass {
        //只能允许有实例成员，不允许静态成员
        String sex;
        //下面代码编译会报错
        //static String name;
        //测试与外部类有同名变量
        int size = 10;

        public void test(int size) {
            System.out.println("方法传入的参数" + size);
            System.out.println("内部类自己的变量" + this.size);
            //同名时调用外部类成员
            System.out.println("外部类的变量：" + InnerClass.this.size);
        }
    }

    /**
     * 静态内部类
     * 不能访问非静态成员
     */
    static class StaticClass {
        public String sex = "男";
        //可以有静态成员
        public static String name = "静态内部类";
    }
}

abstract class Person {
    String sex;
    String name;

    //无参的构造器
    Person() {
        sex = "男";
        name = "无参初始化";
    }

    //有惨的构造器
    Person(String s, String n) {
        sex = s;
        name = n;
    }

    abstract void test();
}
