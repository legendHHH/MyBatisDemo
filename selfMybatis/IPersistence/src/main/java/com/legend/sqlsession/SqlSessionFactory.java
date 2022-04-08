package com.legend.sqlsession;

/**
 * SqlSessionFactory
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2022/4/7
 */
public interface SqlSessionFactory {

    public SqlSession openSession();
}
