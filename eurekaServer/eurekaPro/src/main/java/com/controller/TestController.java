package com.controller;

import com.entity.User;
import com.service.HelloService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController implements HelloService{


    @Value("${server.port}")
    String port;

    @Override
    public String hello(String name) {

        return port;
    }

    @Override
    public User addUser(User user) {

        return user;
    }

    @Override
    public String updateUser(String name) {
        return null;
    }

    @Override
    public String delete(String id) {
        return null;
    }


   /* @GetMapping("/test")
    public String test(String name){

        return "Hello "+name+"_port:"+port;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){

        return user;
    };

    @GetMapping("/testCookie")
    public String testCookie(HttpServletRequest request){

        String cookie = request.getHeader("cookie");

        return "Hello "+cookie;
    }


    @GetMapping("/hello")
    public String hello(String name){

        return "Hello "+name+"_port:"+port;
    };*/
}
