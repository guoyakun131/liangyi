package com.liangyi.service;


import com.liangyi.entity.User;
import com.liangyi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper user;

    public List<User> user(){
       return  user.user();
    }
}
