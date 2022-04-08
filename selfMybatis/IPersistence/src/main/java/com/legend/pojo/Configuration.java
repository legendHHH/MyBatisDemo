package com.legend.pojo;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Configuration
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2022/4/7
 */
public class Configuration {

    private DataSource dataSource;

    /**
     * key：statementId
     * value：封装好的mapperStatement对象
     */
    private Map<String, MapperStatement> mapperStatementMap;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MapperStatement> getMapperStatementMap() {
        return mapperStatementMap;
    }

    public void setMapperStatementMap(Map<String, MapperStatement> mapperStatementMap) {
        this.mapperStatementMap = mapperStatementMap;
    }
}
