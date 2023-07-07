package com.bilibili.community.community.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String find() {
        return "Hibernate";
    }
}
