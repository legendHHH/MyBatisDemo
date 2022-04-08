package com.legend.sqlsession;

import com.legend.config.XmlConfigBuilder;
import com.legend.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * SqlSessionFactoryBuilder
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2022/4/7
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream in) throws DocumentException, PropertyVetoException {
        //第一：使用dom4j解析配置文件，将解析出来的内容封装到Configuration中
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);


        //第二：创建sqlSessionFactory对象。工厂类：生产sqlSession会话对象
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);

        return  null;
    }
}
