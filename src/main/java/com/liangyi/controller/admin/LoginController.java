package com.liangyi.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * admin登录
 */
@Controller
@RequestMapping({"/admin/Login"})
public class LoginController {

    /**
     * 登录页面
     *
     * @return
     */
    @GetMapping("")
    public String login() {
        System.out.println("执行");
        return "views/login";
    }

    /**
     * 验证账号密码
     *
     * @return
     */
    @PostMapping("check")
    public String check() {
        return "redirect:welcome";
    }

    @GetMapping("welcome")
    public String welcome() {
        return "views/welcome";
    }
}
