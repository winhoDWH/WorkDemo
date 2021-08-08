package dwh.dao.impl;

import dwh.dao.JdbcDao;

/**
 * @author: dengwenhao
 * @create: 2021-05-30
 **/
public class MysqlDao implements JdbcDao {
    public void connect() {
        System.out.println("mysql连接");
    }
}
