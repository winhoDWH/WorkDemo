package dwh.entity;

import dwh.dao.JdbcDao;

import java.io.PipedReader;

/**
 * 测试spring容器创建bean对象
 * @author: dengwenhao
 * @create: 2021-05-30
 **/
public class TestBean {
    private String str;

    private String ste;

    private JdbcDao dao;

    public TestBean() {
        System.out.println("使用了无参构造方法");
    }

    public TestBean(String str, JdbcDao dao) {
        System.out.println("使用了有两个参构造方法");
        this.str = str;
        this.dao = dao;
    }

    public TestBean(String str, String ste, JdbcDao dao) {
        this.str = str;
        this.ste = ste;
        this.dao = dao;
    }

    public TestBean(String str) {
        System.out.println("使用了有一个参数的构造方法");
        this.str = str;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "str='" + str + '\'' +
                ", dao=" + dao + ", ste=" + ste +
                '}';
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setDao(JdbcDao dao) {
        this.dao = dao;
    }
}
