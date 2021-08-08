package com.dwh.commonUtil;

import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8 Stream类的工具类
 * 具体使用
 */
public class StreamUtil {
    /**
     * 创建stream
     */
    public void createStream() throws Exception {
        /*
         * 集合（继承了Collection类）的类创建Stream
         */
        List<String> list = new ArrayList<>();
        //串行流
        Stream<String> listStream = list.stream();
        Stream<String> listToPaStream = listStream.parallel();
        //并行流
        Stream<String> paStream = list.parallelStream();


        /*
         * 数组创建流
         */
        String[] strArray = new String[]{"1", "2"};
        Stream<String> stringStream = Arrays.stream(strArray);

        /*
         * 使用Stream类的静态方法
         */
        //of方法-入参为一个数组/多参数(T ...a)或者一个数值(T a)
        Stream<Integer> ofStream = Stream.of(1,2);
        //iterate方法,返回一个无限流
        Stream<Integer> itStream = Stream.iterate(2, n -> 2+n).limit(6);
        //生成2,4,6，8,10,12
        itStream.forEach(n -> System.out.println(n));
        //generate方法，返回一个无限流
        //生成结果为一组随机数
        Stream<Double> genStream = Stream.generate(Math::random).limit(6);

        /*
         * 使用BufferedReader.lines()方法，将一个流中的一行内容转成stream；
         */
        BufferedReader reader = new BufferedReader(new FileReader("F:\\test_stream.txt"));
        Stream<String> lineStream = reader.lines();
        lineStream.forEach(System.out::println);

        /*
         * 使用Pattern.splitAsStream()
         * 将字符串修改成Stream
         */
        //先定义将字符串分隔的分隔符
        Pattern pattern = Pattern.compile(",");
        Stream<String> patStream = pattern.splitAsStream("a,b,c,d");
        stringStream.forEach(System.out::println);
    }

    /**
     * Stream中间操作
     */
    public void middleOperation(){
        List<String> strList = new ArrayList<>();
        strList.add("java");
        strList.add("open");
        strList.add("cat");
        strList.add("do");
        List<Double> doStream = Stream.generate(Math::random).limit(10).collect(Collectors.toList());
        /*
         * 筛选和切片
         */
        //filter过滤
        System.out.println("获取strList中字符长度大于3的");
        strList.stream().filter(o -> o.length()>3).forEach(o -> System.out.print(o +","));
        System.out.println();
        //limit 取一个流中固定个数
        doStream.stream().limit(2).forEach(System.out::print);
        System.out.println();
        //如果取的个数大于流中元素个数,则取全部元素，并不报错或补默认值
        System.out.println("limit取intStream中11个元素后，新流中元素个数为：" + doStream.stream().limit(11).count());
        System.out.println("limit取intStream中11个元素后，新流中元素为：");
        doStream.stream().limit(11).forEach(o -> System.out.print(o +","));
        System.out.println();
        //skip,配合limit实现分页
        System.out.println("取intStream中第2个元素后3个元素，新流中元素为：");
        doStream.stream().skip(1).limit(3).forEach(o -> System.out.print(o +","));
        //distinct
        doStream.stream().distinct();

        /*
         * 映射 map和flatmap
         */

        List<String> list = Arrays.asList("a,b,c", "1,2,3");

        //将每个元素转成一个新的且不带逗号的元素
        Stream<String> s1 = list.stream().map(s -> s.replaceAll(",", ""));
        //输出 abc  123
        System.out.println("测试map");
        s1.forEach(System.out::print);
        System.out.println();

        Stream<String> s3 = list.stream().flatMap(s -> {
            //将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        });
        //输出 a b c 1 2 3
        System.out.println("测试flatMap");
        s3.forEach(System.out::print);
        System.out.println();

        /*
         * 排序
         */
        List<String> sortlist = Arrays.asList("aa", "ff", "dd");
        //String 类自身已实现Compareable接口
        //输出 aa dd ff
        System.out.println("测试sorted");
        sortlist.stream().sorted().forEach(System.out::print);
        System.out.println();

        List<Person> personList = new ArrayList<>();
        personList.add(new Person(11, "lihua", 3));
        personList.add(new Person(11, "全相同", 3));
        personList.add(new Person(3, "equal相同，hashcode不同", 11));
        personList.add(new Person(11, "equal不同，hashcode相同", 2));

        System.out.println("测试distinct");
        personList.stream().distinct().forEach(o -> System.out.print(o.name + ";"));
        System.out.println();
    }

    /**
     * 流的终止操作
     */
    public void finishOperation(){
        List<String> strList = new ArrayList<>();
        strList.add("java");
        strList.add("open");
        strList.add("cat");
        strList.add("do");
        List<Double> doStream = Stream.generate(Math::random).limit(10).collect(Collectors.toList());

        //所有匹配
        boolean allMath =strList.stream().allMatch(o -> o.contains("a"));
        //部分匹配
        boolean anyMath = strList.stream().anyMatch(o -> o.contains("a"));
        //都不匹配
        boolean noneMath = strList.stream().noneMatch(o -> o.contains("a"));
        System.out.println("查看strList中是否有包含字母a的字符串，所有匹配allMath:" + allMath
            + ",部分匹配：" + anyMath + "，都不匹配：" + noneMath);

        System.out.println("doubleStream中元素：");
        doStream.forEach(o ->System.out.print(o + ","));
        System.out.println();
        System.out.println("获取doubleStream中第一个元素" + doStream.stream().findFirst().get());
        System.out.println("获取doubleStream中任意元素" + doStream.stream().findAny().get());
        System.out.println("获取doubleStream中元素个数" + doStream.stream().count());
        System.out.println("获取doubleStream中最大元素" + doStream.stream().max(Double::compareTo).get());
        System.out.println("获取doubleStream中最小元素" + doStream.stream().min(Double::compareTo).get());

        Object[] objects = doStream.stream().toArray();
        Double[] doubles = doStream.stream().toArray(Double[]::new);
        doStream.toArray();

    }

    public void collectOperation(){
        List<Apple> data = Arrays.asList(new Apple("green", 210),
                new Apple("red", 170), new Apple("green", 100), new Apple("red", 170),
                new Apple("yellow", 170), new Apple("green", 150));

        /*
         * 求平均值 `Collectors.averagingInt(x)`,`Collectors.averagingLong(x)`以及`Collectors.averagingDouble(x)`
         */
        //这里由于集合用来求值的元素类型为int所以使用Collectors.averagingInt(x)
        //Apple::getWeight说明怎么取流中元素用来计算的值
        //注意返回值为Double
        double avg = data.stream().collect(Collectors.averagingInt(Apple::getWeight));
        //统计
        Long count = data.stream().collect(Collectors.counting());
        //求多种值（包含和、平均值、最大最小值、元素个数）
        IntSummaryStatistics summaryStatistics = data.stream().collect(Collectors.summarizingInt(Apple::getWeight));
        //输出IntSummaryStatistics{count=6, sum=970, min=100, average=161.666667, max=210}
        System.out.println("求多类值summarizingInt结果：" + summaryStatistics);

        /*
         * groupingBy分组
         */
        //单个参数
        Map<String, List<Apple>> groupCollector1 = data.stream().collect(Collectors.groupingBy(Apple::getName));
        //两个参数
        Map<String, Long> groupCollector2 = data.stream().collect(Collectors.groupingBy(Apple::getName, Collectors.counting()));
        //三个参数
        TreeMap<String, Long> groupCollector3 = data.stream().collect(Collectors.groupingBy(Apple::getName, TreeMap::new,Collectors.counting()));

        /*
         * partitioningBy分区
         */
        //两个参数，求分组后各自的平均值
        Map<Boolean, Double> collect = data.stream().collect(Collectors.partitioningBy(
                        apple -> "green".equals(apple.getName()),
                        Collectors.averagingInt(Apple::getWeight)));

        /*
         * 字符串拼接joining
         */
        //先使用map()将流中元素都转换成String
        String result = data.stream()
                .map(Apple::getName).collect(Collectors.joining(",", "Color[", "]"));
        System.out.println(result);


    }

    public static void main(String[] args) {
        //new StreamUtil().middleOperation();
        //new StreamUtil().finishOperation();
        //new StreamUtil().collectOperation();

    }


    private static class Person{
        private int age;
        private String name;
        private int per;

        public Person(int age, String name, int per) {
            this.age = age;
            this.name = name;
            this.per = per;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPer() {
            return per;
        }

        public void setPer(int per) {
            this.per = per;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return (((Person) o).getAge()*((Person) o).getPer()) == (age * per);
        }

        @Override
        public int hashCode() {
            return age + 3;
        }
    }

    private static class Apple{
        private String name;
        private int weight;

        public Apple(String name, int weight) {
            this.name = name;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
