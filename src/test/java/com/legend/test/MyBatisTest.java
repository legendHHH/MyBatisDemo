package com.legend.test;

import com.legend.io.Resource;
import com.legend.sqlSession.SqlSessionFactory;
import com.legend.sqlSession.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * test
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2022/4/7
 */
public class MyBatisTest {

    public void test() {
        InputStream resourceAsStream = Resource.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

    }
}
