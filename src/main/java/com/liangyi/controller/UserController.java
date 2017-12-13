package com.liangyi.controller;

import com.liangyi.entity.User;
import com.liangyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/userList")
    public List<User> userList(){
        return userService.user();
    }
}
