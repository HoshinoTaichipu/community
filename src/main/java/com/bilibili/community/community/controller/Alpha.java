package com.bilibili.community.community.controller;

import com.bilibili.community.community.entity.testUser;
import com.bilibili.community.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.Param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class Alpha {
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("Hello!SpringBoot");
        return "Hello,Spring";
    }

    @ResponseBody
    @RequestMapping("/dataFind")
    public String find(){
        return alphaService.find();
    }

    //使用httpservletrequest和httpservletresponse进行请求的处理与响应
    @RequestMapping(path="/testget1",method= RequestMethod.GET)
    public void testGetWithoutResponseBody(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String method = httpServletRequest.getMethod();
        String contextPath = httpServletRequest.getContextPath();
        System.out.println(method + " " + contextPath);

        Enumeration<String> headers = httpServletRequest.getHeaderNames();
        while(headers.hasMoreElements()){
            String header = headers.nextElement();
            String value = httpServletRequest.getHeader(header);
            System.out.println(header + ":" + value);
        }

        try (
                PrintWriter writer = httpServletResponse.getWriter();
                ){
            writer.println("Hello!Http");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(path="/testget2",method=RequestMethod.GET)
    @ResponseBody
    public String testGet2(){
        return "WOW";
    }
    //通过网址传参
    @RequestMapping(path="/testget3",method=RequestMethod.GET)
    @ResponseBody
    public String testGet3(String name,int age){
        System.out.println(name + " "+age);
        return name + " " + age;
    }
    //通过网址以键值对传参并设定默认值
    @RequestMapping(path="/testget4",method = RequestMethod.GET)
    @ResponseBody
    public String testGet4(
            @RequestParam(name = "name",required = false,defaultValue = "zhangsan") String name,
            @RequestParam(name = "age" ,required = false,defaultValue = "1") int age
            ){
        System.out.println(name + " "+age);
        return name + " " + age;
    }
    //通过网址以路径传参
    @RequestMapping(path = "/testGet5/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String testGet5(@PathVariable("id") int id){
        System.out.println(id);
        return id + "";
    }

    //通过post方法将传入的表格进行参数赋值
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String savestudent(@RequestParam(name = "name") String name,@RequestParam(name = "age") int age){
        System.out.println(name + ":" + age);
        return "success";

    }
    //利用thymeleaf进行动态的响应html
    /*@RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getView(){
        ModelAndView view = new ModelAndView();
        view.addObject("name","zhangsan");
        view.addObject("age",20);
        view.setViewName("/demo/teacher");
        return view;
    }*/
    //利用model进行动态响应html
    @RequestMapping(path="/school",method = RequestMethod.GET)
    public String getSchool(Model model, testUser user){


        return "/demo/teacher";
    }

    //响应json数据
    @RequestMapping(path="/getJson",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getJson(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age",18);
        map.put("address","beijing");
        list.add(map);

        map = new HashMap<>();
        map.put("name","lisi");
        map.put("age",19);
        map.put("address","beijing");
        list.add(map);
        return list;
    }

}
