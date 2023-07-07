package com.bilibili.community.community;

import com.bilibili.community.community.dao.AlphaDao;
import com.bilibili.community.community.dao.UserMapper;
import com.bilibili.community.community.entity.User;
import com.bilibili.community.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests  {
	@Autowired
	private UserMapper usermapper;

	@Test
	public void selectTest(){
		User user = usermapper.selectById(123);
		System.out.println(user);
	}
}
