package com.bilibili.community.community.entity;

public class testUser {
    private String name = "zhangsan";
    private int age = 18;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "testUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
