package com.legend.sqlsession;

import com.legend.config.BoundSql;
import com.legend.pojo.Configuration;
import com.legend.pojo.MapperStatement;
import com.legend.utils.GenericTokenParser;
import com.legend.utils.ParameterMapping;
import com.legend.utils.ParameterMappingTokenHandler;
import com.legend.utils.TokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SimpleExecutor
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2022/4/8
 */
public class SimpleExecutor implements Executor {

    /**
     * parameter就是传进来的user类
     *
     * @param configuration
     * @param mapperStatement
     * @param parameter
     * @param <E>
     * @return
     * @throws Exception
     */
    @Override
    public <E> List<E> query(Configuration configuration, MapperStatement mapperStatement, Object... parameter) throws Exception {
        //1.注册驱动，获取连接
        Connection connection = configuration.getDataSource().getConnection();

        //2.获取sql语句：select * from user where id = #{id} and username = #{username}
        //转化sql语句 select * from user where id = ? and username = ? 转化过程中，还需要对#{} 里面的值进行解析存储
        String sql = mapperStatement.getSql();

        BoundSql boundSql = this.getBoundSql(sql);

        //3.获取预处理对象：PreparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        //4.设置参数
        //获取到了参数的全路径
        String parameterType = mapperStatement.getParameterType();
        //转化成Class
        Class<?> parameterTypeClass = this.getClassType(parameterType);

        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();

            //使用反射
            Field declaredField = parameterTypeClass.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(parameter[0]);
            //因为i 从0开始，但是设置值要从1开始  preparedStatement.setString(1, parameter.toString());
            preparedStatement.setObject(i + 1, o);
        }

        //5.执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mapperStatement.getResultType();
        Class<?> resultTypeClass = this.getClassType(resultType);
        Object o = resultTypeClass.newInstance();

        List<Object> list = new ArrayList<>();

        //6.封装返回结果集
        while (resultSet.next()) {
            //元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            //列数从1开始
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                //字段名
                String columnName = metaData.getColumnName(i);
                //字段的值
                Object value = resultSet.getObject(columnName);

                //使用反射或者内省，根据数据库表和实体的对应关系，完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, value);
            }
            list.add(o);
        }
        return (List<E>) list;
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if (parameterType != null) {
            Class<?> aClass = Class.forName(parameterType);
            return aClass;
        }
        return null;
    }

    /**
     * 完成对#{} 的解析工作：1.将#{} 使用? 进行代替 2.解析出#{} 里面的值进行存储
     *
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        //标记处理类：配置标记解析器来完成对占位符
        //TokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        //解析出来的sql
        String parseSql = genericTokenParser.parse(sql);
        //#{} 解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);
        return boundSql;
    }

}
