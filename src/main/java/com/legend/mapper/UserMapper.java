package com.legend.mapper;

import com.legend.entity.User;

import java.util.List;

/**
 * UserMapper接口
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2022/4/7
 */
public interface UserMapper {
    List<User> selectList();

    User selectOne(String id);
}
