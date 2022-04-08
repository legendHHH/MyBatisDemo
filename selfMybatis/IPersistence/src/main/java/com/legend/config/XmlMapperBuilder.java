package com.legend.config;

import com.legend.pojo.Configuration;
import com.legend.pojo.MapperStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * XML处理mapper文件
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2022/4/8
 */
public class XmlMapperBuilder {
    private Configuration configuration;

    public XmlMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        String namespace = rootElement.attributeValue("namespace");
        List<Element> selectElementList = rootElement.selectNodes("//select");
        for (Element el : selectElementList) {
            String id = el.attributeValue("id");
            String resultType = el.attributeValue("resultType");
            String parameterType = el.attributeValue("parameterType");
            String sqlText = el.getTextTrim();
            MapperStatement mapperStatement = new MapperStatement();
            mapperStatement.setId(id);
            mapperStatement.setResultType(resultType);
            mapperStatement.setParameterType(parameterType);
            mapperStatement.setSql(sqlText);

            String key = namespace +"." + id;
            configuration.getMapperStatementMap().put(key, mapperStatement);
        }
    }
}
