package com.legend.sqlsession;

import java.util.List;

/**
 * SqlSession
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2022/4/8
 */
public interface SqlSession {
    /**
     * 查询所有
     *
     * @param statementId
     * @param params
     * @param <E>
     * @return
     */
    public <E> List<E> selectList(String statementId, Object... params);

    /**
     * 根据条件查询单个
     *
     * @param statementId
     * @param params
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statementId, Object... params);
}
