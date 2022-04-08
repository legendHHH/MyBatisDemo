package com.legend.sqlsession;

import com.legend.pojo.Configuration;
import com.legend.pojo.MapperStatement;

import java.util.List;

/**
 * DefaultSqlSession
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2022/4/8
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) {
        //将要去完成对simpleExecutor里的query方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MapperStatement mapperStatement = configuration.getMapperStatementMap().get(statementId);
        List<Object> list = simpleExecutor.query(configuration, mapperStatement, params);
        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) {
        List<Object> objects = selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为空或者是多个结果数据");
        }
    }
}
