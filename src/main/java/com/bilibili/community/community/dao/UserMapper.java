package com.bilibili.community.community.dao;

import com.bilibili.community.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {

    //Select查询
    User selectById(int id);

    User selectByEmail(String email);

    User selectByUsername(String userName);

    //Update更新

    int updateStatus(int id,int status);

    int updatePassword(int id,String password);

    int updateHeaderUrl(int id,String headerUrl);

    //insert插入

    int insertUser(User user);
}
