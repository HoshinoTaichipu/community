package com.bilibili.community.community;

import com.bilibili.community.community.dao.DiscussPostMapper;
import com.bilibili.community.community.dao.UserMapper;
import com.bilibili.community.community.entity.DiscussPost;
import com.bilibili.community.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MybatisTest {
    @Autowired
    private UserMapper usermapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void selectTest(){
        User user = usermapper.selectById(123);
        System.out.println(user);
        user = usermapper.selectByEmail("nowcoder123@sina.com");
        System.out.println(user);
        user = usermapper.selectByUsername("nnn");
        System.out.println(user);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setActivationCode("0");
        user.setCreateTime(new Date());
        user.setSalt("aaaa");
        user.setHeaderUrl("http://www.tupian.com/101");
        user.setEmail("1156917440@qq.com");
        user.setStatus(0);
        user.setType(0);

        int row = usermapper.insertUser(user);
        System.out.println(row);
    }


    @Test
    public void testUpdate(){
        int row = usermapper.updateStatus(150, 1);
        System.out.println(row);
        row = usermapper.updatePassword(150, "111111");
        System.out.println(row);
        row = usermapper.updateHeaderUrl(150, "http://image/101");
        System.out.println(row);


    }
    @Test
    public void testSelectDiscussPost(){
        List<DiscussPost> discussPosts = discussPostMapper.selectByUserId(0, 0, 10);
        for(DiscussPost discusspost : discussPosts){
            System.out.println(discusspost);
        }
        int rows = discussPostMapper.selectByUserIdRows(0);
        System.out.println(rows);

    }
}
