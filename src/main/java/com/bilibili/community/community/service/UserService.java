package com.bilibili.community.community.service;

import com.bilibili.community.community.dao.UserMapper;
import com.bilibili.community.community.entity.User;
import com.bilibili.community.community.util.CommunityConstant;
import com.bilibili.community.community.util.CommunityUtil;
import com.bilibili.community.community.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailClient mailClient;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${community.path.domain}")
    private String domainPath;

    @Value("${server.servlet.context-path}")
    private String projectPath;

    public User findUser(int id){
        return userMapper.selectById(id);
    }
    //返回map的原因是可能会返回多种错误信息，需要一个能封装多个错误信息的容器
    //且因为是业务层不会使用到model进行封装，因此使用Map，返回给controller层后由model封装map
    public Map<String,Object> register(User user) {
        Map<String,Object> map = new HashMap<>();
        //查询用户传入的参数是否为空
        if(StringUtils.isBlank(user.getUsername())){
            map.put("usernameMsg","用户名不能为空！");
            return map;
        }
        if(StringUtils.isBlank(user.getPassword())){
            map.put("userpasswordMsg","密码不能为空！");
            return map;
        }
        if(StringUtils.isBlank(user.getEmail())){
            map.put("useremailMsg", "邮箱不能为空");
        }

        //查询用户传入的用户名是否有重复
        User u = userMapper.selectByUsername(user.getUsername());
        if(u != null) {
            map.put("usernameMsg","用户名重复,请重新输入新的用户名");
            return map;
        }
        User s = userMapper.selectByEmail(user.getEmail());
        if(s != null){
            map.put("useremailMsg","邮箱重复，请重新输入");
            return map;
        }

        //注册
        user.setType(0);
        user.setStatus(0);
        user.setSalt(CommunityUtil.generateUUID().substring(0,5));
        user.setCreateTime(new Date());
        user.setActivationCode(CommunityUtil.generateUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setPassword(CommunityUtil.md5(user.getPassword() + user.getSalt()));
        //插入到数据库中，并将id返回回来填充在user中
        userMapper.insertUser(user);

        //发送邮件
        //http://localhost:8080/community/activation/{id}/{activationCode}
        Context context = new Context();
        context.setVariable("user",user);
        String url = domainPath + projectPath +"/activation/" + user.getId() +"/" + user.getActivationCode();
        context.setVariable("url", url);

        String str = templateEngine.process("/mail/activation",context);
        mailClient.sendMail(user.getEmail(), "激活邮件", str);

        return map;
    }

    public int activation(int id,String code){
        User user = userMapper.selectById(id);
        if(code.equals(user.getActivationCode())){
            if(user.getStatus() == 0){
                userMapper.updateStatus(id, 1);
                return CommunityConstant.ACTIVATION_SUCCESS;
            }else{
                return CommunityConstant.ACTIVATION_REPEAT;
            }
        }else{
            return CommunityConstant.ACTIVATION_ERROR;
        }
    }
}
