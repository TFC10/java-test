package org.example.mapper;

import org.example.pojo.User;

import java.util.List;

public interface UserMapper {

    int insertUser();

    void updateUser();

    void deleteUser();
    //根据id查询用户信息
    User getUserId();

    //查询所有的用户的信息
    List<User> getAllUser();
}
