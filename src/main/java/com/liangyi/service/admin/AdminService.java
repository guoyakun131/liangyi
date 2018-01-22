package com.liangyi.service.admin;

import com.liangyi.entity.User;
import com.liangyi.mapper.AdminMapper;
import com.liangyi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.ls.LSInput;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 修改管理员密码
     *
     * @param userName
     * @param newpw
     * @param newpwc
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean changePw(String userName, String newpw, String newpwc) {
        if (newpw != null && newpw.equals(newpwc)) {
            adminMapper.changePw(userName, newpw);
            System.out.println(111);
            return true;
        }
        return false;
    }

    public List<User> userList() {
        List<User> userList = userMapper.userList();
        return userList;
    }
}
