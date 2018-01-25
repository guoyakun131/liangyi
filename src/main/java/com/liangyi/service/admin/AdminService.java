package com.liangyi.service.admin;

import com.liangyi.entity.G;
import com.liangyi.entity.User;
import com.liangyi.mapper.AdminMapper;
import com.liangyi.mapper.GMapper;
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

    @Autowired
    private GMapper gMapper;

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

    /**
     * 用户列表
     *
     * @return
     */
    public List<User> userList(String nickName) {
        if (nickName == null) {
            return userMapper.userList();
        } else {
            return userMapper.userLsitByName(nickName);
        }
    }

    /**
     * 通知栏列表
     *
     * @return
     */
    public List<G> g() {
        return gMapper.gList();
    }

    /**
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String saveG(Integer id, String text) {
        if (id == null) {
            gMapper.saveG(text);
            return "添加成功";
        } else {
            gMapper.upG(text, id);
            return "修改成功";
        }
    }

    /**
     * 编辑查询
     *
     * @param id
     * @return
     */
    public G up(int id) {
        return gMapper.up(id);
    }

    /**
     * 通告状态修改
     * @param start
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String satrt(int start, int id) {
        try {
            gMapper.gStart(start, id);
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    /**
     * 删除通知栏
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String gremoe(int id){
        try{
            gMapper.gremoe(id);
            return "成功";
        }catch (Exception e){
            e.printStackTrace();
            return "失败";
        }
    }
}
