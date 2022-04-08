package com.legend.config;

import com.legend.io.Resource;
import com.legend.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * XML配置
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2022/4/7
 */
public class XmlConfigBuilder {

    private Configuration configuration;

    public XmlConfigBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * 该方法就是使用dom4j将配置文件进行解析，封装成Configuration对象
     *
     * @param inputStream
     * @return
     */
    public Configuration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(inputStream);
        //<configuration>
        Element rootElement = document.getRootElement();
        //<property>
        List<Element> list = rootElement.selectNodes("//property");

        Properties properties = new Properties();
        for (Element el : list) {
            String name = el.attributeValue("name");
            String value = el.attributeValue("value");
            properties.setProperty(name, value);
        }

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));

        configuration.setDataSource(comboPooledDataSource);

        //mapper.xml解析：拿到路径-字节输入流-dom4j进行解析
        List<Element> mapperList = rootElement.selectNodes("//mapper");
        for (Element el : mapperList) {
            String mapperPath = el.attributeValue("resource");
            InputStream resourceAsStream = Resource.getResourceAsStream(mapperPath);
            XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsStream);
        }
        return configuration;
    }
}
