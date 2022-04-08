package com.legend.sqlsession;

import com.legend.pojo.Configuration;
import com.legend.pojo.MapperStatement;

import java.util.List;

/**
 * Executor
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2022/4/8
 */
public interface Executor {
    public <E> List<E> query(Configuration configuration, MapperStatement mapperStatement, Object... parameter) throws Exception;
}
