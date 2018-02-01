package com.liangyi.service.admin;


import com.liangyi.entity.Admin;
import com.liangyi.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */
public class CustomUserService implements UserDetailsService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Admin admin = adminMapper.adminInfo(s);
        if (admin == null) {
            throw new UsernameNotFoundException("用户名或者密码错误！");
        }
        System.out.println("s:" + s);
        System.out.println("username:" + admin.getUsername() + ";password:" + admin.getPassword());
        //GrantedAuthority是security提供的权限类，
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        //获取角色，放到list里面
        // getRoles(admin, list);
        //返回包括权限角色的User给security
        org.springframework.security.core.userdetails.User auth_user = new
                org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), list);
        return auth_user;
    }
/**
 27      * 获取所属角色
 28      * @param user
 29      * @param list
 30      */
//     public void getRoles(Admin admin, List<GrantedAuthority> list){
//                for (String role:admin.getName().split(",")) {
//                    //权限如果前缀是ROLE_，security就会认为这是个角色信息，而不是权限，例如ROLE_MENBER就是MENBER角色，CAN_SEND就是CAN_SEND权限
//                       list.add(new SimpleGrantedAuthority("ROLE_"+role));
//                   }
//            }
}

