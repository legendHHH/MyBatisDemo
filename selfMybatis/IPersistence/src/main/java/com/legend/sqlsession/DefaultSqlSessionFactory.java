package com.legend.sqlsession;

import com.legend.pojo.Configuration;

/**
 * DefaultSqlSessionFactory
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2022/4/8
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
