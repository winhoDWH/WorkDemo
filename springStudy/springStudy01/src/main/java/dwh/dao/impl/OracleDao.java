package dwh.dao.impl;

import dwh.dao.JdbcDao;

/**
 * @author: dengwenhao
 * @create: 2021-05-30
 **/
public class OracleDao implements JdbcDao {
    public void connect() {
        System.out.println("oracle连接");
    }
}
