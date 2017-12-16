package com.liangyi.service;


import com.liangyi.entity.User;
import com.liangyi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
     * 查询用户是否存在
     * @param open_id
     * @return
     */
    public boolean isUser(String open_id){
        //查询用户是否存在
        String userOpenid = userMapper.selectUser(open_id);
        System.out.println("userOpenid"+userOpenid);
        if(userOpenid == null){
         return false;
        }
        return true;
    }

    /**
     * 添加用户信息
     * @param avatar
     * @param nick_name
     * @param open_id
     */
    @Transactional(propagation = Propagation.REQUIRED)
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
