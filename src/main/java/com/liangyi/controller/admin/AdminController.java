package com.liangyi.controller.admin;

import com.liangyi.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@Controller
@RequestMapping({"/admin"})
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("change_pw")

    public String changePw(HttpServletRequest request) {
        //当前登录的用户
        String username = request.getRemoteUser();
        //新密码
        String newpw = request.getParameter("newpw");
        //确认新密码
        String newpwc = request.getParameter("newpwc");
        System.out.println("username:"+username+"newpw:"+newpw+"newpwc:"+newpwc);
        adminService.changePw(username, newpw, newpwc);
        return "redirect:/admin/Login";
    }
}
