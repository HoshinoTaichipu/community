package com.bilibili.community.community.dao;

import com.bilibili.community.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectByUserId(int userId, int offset, int limit);

    int selectByUserIdRows(@Param("userId") int userId);

}
