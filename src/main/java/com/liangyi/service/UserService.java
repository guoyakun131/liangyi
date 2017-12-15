package com.liangyi.service;


import com.liangyi.entity.User;
import com.liangyi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> user() {
        return userMapper.user();
    }

    /**
     * 添加用户信息
     *
     * @param avatar
     * @param nick_name
     * @param open_id
     */
    public void addUser(String avatar, String nick_name, String open_id) {
        //系统时间
        Date d = new Date();
        //格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        //转int
        int add_time = Integer.valueOf(sdf.format(d).toString());
        //添加到数据库
        userMapper.addUser(avatar, nick_name, open_id, add_time);

    }

}
