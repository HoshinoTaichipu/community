package com.bilibili.community.community.service;

import com.bilibili.community.community.dao.AlphaDaoMybatisImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class AlphaService {

    @Autowired
    private AlphaDaoMybatisImpl alphaDaoMybatisImpl;

    public String find(){
        return alphaDaoMybatisImpl.find();
    }

    @PostConstruct
    public void init() {
        System.out.println("Service初始化");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Service销毁");
    }
}
