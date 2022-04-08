package com.legend.test;

import com.legend.entity.User;
import com.legend.io.Resource;
import com.legend.sqlsession.SqlSession;
import com.legend.sqlsession.SqlSessionFactory;
import com.legend.sqlsession.SqlSessionFactoryBuilder;

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

    public void test() throws Exception {
        InputStream resourceAsStream = Resource.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId("1");
        user.setUsername("666");
        User u = sqlSession.selectOne("com.legend.mapper.UserMapper.selectOne", user);
    }
}
