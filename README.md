### 自定义持久层框架设计思路
#### 使用端（项目）
`引入自定义持久层框架的jar包`
- 1.提供两部分配置信息：数据库配置信息、sql配置信息：sql语句、参数类型、返回值类型
- 2.使用配置文件来提供两部分配置信息：
    （1）sqlMapConfig.xml：存放数据库配置信息。还可以指定mapper.xml的全路径信息
    （2）mapper.xml：存放sql配置信息

#### 自定义持久层框架本身（工程）
`本质就是对JDBC代码进行了封装`

- 1.加载配置文件：根据配置文件的路径，加载配置文件成字节输入流，存储在内存中
    - 创建Resource类 方法：InputStream getResourceAsStream(String path)

- 2.创建两个JavaBean：（容器对象）存放的就是对配置文件解析出来的内容
    - Configuration：核心配置类，存放的是sqlMapConfig.xml解析出来的内容
    - MapperStatement：映射配置类，存放的是mapper.xml解析出来的内容
    
- 3.解析配置文件dom4j
    - 创建类SqlSessionFactoryBuilder  方法：build(InputStream in)
    第一：使用dom4j解析配置文件，将解析出来的内容封装到容器对象中
    第二：创建SqlSessionFactory对象；生产sqlSession(会话对象-工厂模式-可以降低耦合)
    
    
- 4.创建SqlSessionFactory接口及实现类DefaultSqlSessionFactory
    - 第一：openSession()：生产sqlSession

- 5.创建SqlSession接口及实现类DefaultSqlSession
    - 定义对数据库的CRUD操作：selectList()、delete()、update()、
    
- 6.创建Executor接口及实现类SimpleExecutor实现类
    - query(Configuration，MapperStatement，Object...params)：执行就是JDBC代码        