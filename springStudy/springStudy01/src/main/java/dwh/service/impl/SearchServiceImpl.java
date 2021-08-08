package dwh.service.impl;

import dwh.dao.JdbcDao;
import dwh.service.SearchService;

/**
 * 服务实现类
 * @author: dengwenhao
 * @create: 2021-05-30
 **/
public class SearchServiceImpl implements SearchService {

    private JdbcDao dao;

    private int number;

    private String str;

    private boolean flag;

    /**
     * 需要写这个方法，因为是使用xml配置bean
     * @param dao
     */
    public void setDao(JdbcDao dao) {
        this.dao = dao;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void connect() {
        dao.connect();
        System.out.println("number:" + number + "；flag:" + flag + "; str:" + str);
    }
}
