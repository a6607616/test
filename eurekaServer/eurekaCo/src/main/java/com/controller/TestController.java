package com.controller;

import com.entity.User;
import com.service.FeignHelloService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {


    @Autowired
    FeignHelloService feignHelloService;

    @Autowired
    DiscoveryClient discoveryClient;


    RestTemplate restTemplate;

    int count=0;


    @GetMapping("/hello")
    public String hello(String name){

        String hello = feignHelloService.hello(name);

        return hello;
    };

    @PostMapping("/addUser")
    public User addUser( ){

        User user=new User();

        user.setId("1");

        user.setName("刘涛");

        return  feignHelloService.addUser(user);
    };

    /*@PutMapping("/updateUser")
    public String updateUser(@RequestParam("name") String name){

    };

    @DeleteMapping("/deleteUser/{id}")
    public  String delete(@PathVariable("id") String id){

    };*/

















    @GetMapping("/test")
    public String test(String name){


       /* List<ServiceInstance> provider = discoveryClient.getInstances("provider");

        ServiceInstance serviceInstance = provider.get(count%provider.size());

        String host = serviceInstance.getHost();

        int port = serviceInstance.getPort();*/

        Map<String,String> param=new HashMap<>();

        param.put("name","fuck");

        String forObject = restTemplate.getForObject("http://provider/test?name={name}", String.class, param);

        count++;

        return forObject;
    }

    /*@GetMapping("/test")
    @ResponseBody
    public String test(String name){


        List<ServiceInstance> provider = discoveryClient.getInstances("provider");

        ServiceInstance serviceInstance = provider.get(count%provider.size());

        String host = serviceInstance.getHost();

        int port = serviceInstance.getPort();

        Map<String,String> param=new HashMap<>();

        param.put("name","fuck");

        String forObject = restTemplate.getForObject("http://" + host + ":" + port + "/test?name={name}", String.class, param);

        count++;

        return forObject;
    }*/


    @GetMapping("/testCookie")
    public String testCookie(String name){


        List<ServiceInstance> provider = discoveryClient.getInstances("provider");

        ServiceInstance serviceInstance = provider.get(0);

        String host = serviceInstance.getHost();

        int port = serviceInstance.getPort();

        String uri="/testCookie";


        restTemplate.setInterceptors(Collections.singletonList((request,bytes,execution)->{

            HttpHeaders headers = request.getHeaders();

            headers.add("cookie","justdojava");

            return execution.execute(request,bytes);
        }));

        String forObject = restTemplate.getForObject("http://" + host + ":" + port + uri, String.class);

        return forObject;
    }
}
