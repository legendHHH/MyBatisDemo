package com.legend.io;

import java.io.InputStream;

/**
 * Resource 根据配置文件的路径，将配置文件加载成字节输入流，存储在内存中
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2022/4/7
 */
public class Resource {

    public static InputStream getResourceAsStream(String path){
        InputStream resourceAsStream = Resource.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
