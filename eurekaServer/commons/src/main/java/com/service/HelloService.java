package com.service;

import com.entity.User;
import org.springframework.web.bind.annotation.*;

public interface HelloService {

    @GetMapping("/hello")
    String hello(@RequestParam("name") String name);

    @PostMapping("/addUser")
    User addUser(@RequestBody User user);

    @PutMapping("/updateUser")
    String updateUser(@RequestParam("name") String name);

    @DeleteMapping("/deleteUser/{id}")
    String delete(@PathVariable("id") String id);
}
