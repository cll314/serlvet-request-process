package com.dsdj.requestlearn.domain;

/**
 * @ClassName User
 * @Description TODO
 * @Author ChenLiLin
 * @Date 2018/10/10 9:38
 * @Version 1.0
 **/
public class User {
    private String age;
    private String name;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age='" + age + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}