package com.legend.config;

import com.legend.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * BoundSql
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2022/4/8
 */
public class BoundSql {

    /**
     * 解析过后的sql
     */
    private String sqlText;
    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
