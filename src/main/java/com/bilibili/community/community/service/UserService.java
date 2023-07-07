package com.bilibili.community.community.service;

import com.bilibili.community.community.dao.UserMapper;
import com.bilibili.community.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUser(int id){
        return userMapper.selectById(id);
    }
}
