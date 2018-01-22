package com.liangyi.controller.admin;

import com.liangyi.service.UserService;
import com.liangyi.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping({"/User"})
public class AdminUserController {
@Autowired
    private AdminService adminService;

    @GetMapping("index")
    public String userIndex(Model model) {
        model.addAttribute("userList", adminService.userList());
        return "views/user/index";
    }
}
