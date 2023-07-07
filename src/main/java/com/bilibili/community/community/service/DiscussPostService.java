package com.bilibili.community.community.service;

import com.bilibili.community.community.dao.DiscussPostMapper;
import com.bilibili.community.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostService {
    @Autowired
    DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit){
        return discussPostMapper.selectByUserId(userId, offset, limit);
    }
    public int findDiscussPostsRows(int userId){
        return discussPostMapper.selectByUserIdRows(userId);
    }
}
