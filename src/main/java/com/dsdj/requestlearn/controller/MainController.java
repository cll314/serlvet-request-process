package com.dsdj.requestlearn.controller;

import com.dsdj.requestlearn.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName MainController
 * @Description TODO
 * @Author ChenLiLin
 * @Date 2018/10/8 17:00
 * @Version 1.0
 **/
@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String Main(){
        return "index";
    }


    @PostMapping(value = "/user")
    public String addUser(@RequestBody User user){
        System.out.println(user);
        return "user";
    }
    @PostMapping(value = "/user2.body")
    public String addUserBody2(HttpServletRequest request, String age, String name){
        System.out.println("-----表单的数据-----");
        System.out.println("age:"+age+";name:"+name);
        System.out.println();
        System.out.println();
        System.out.println();
        return "user";
    }
    @PostMapping(value = "/user.body")
    public String addUserBody(@RequestBody User user, HttpServletRequest request){
        System.out.println("-----JSON的数据-----");
        System.out.println("user=="+user);
        System.out.println();
        System.out.println();
        System.out.println();
        return "user";
    }
}