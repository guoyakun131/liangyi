package com.liangyi.controller.admin;

import com.liangyi.service.admin.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.soap.Addressing;

/**
 * admin登录
 */
@Controller
@RequestMapping({"/admin"})
public class LoginController {

    @Addressing
    private LoginService loginService;
    /**
     * 登录页面
     *
     * @return
     */
//    @GetMapping("")
//    public String login() {
//        System.out.println("执行");
//        return "views/login";
//    }

    /**
     * 验证账号密码
     *
     * @return
     */
//    @PostMapping("check")
//    public String check() {
//        System.out.println("check");
//        return "redirect:welcome";
//    }

//    @GetMapping("welcome")
//    public String welcome() {
//        return "views/welcome";
//    }
}
