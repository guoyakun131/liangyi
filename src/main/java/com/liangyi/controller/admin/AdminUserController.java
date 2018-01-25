package com.liangyi.controller.admin;

import com.liangyi.service.UserService;
import com.liangyi.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping({"/User"})
public class AdminUserController {
    @Autowired
    private AdminService adminService;

    /**
     * 用户列表
     *
     * @param model
     * @return
     */
    @GetMapping("index")
    public String userIndex(String nickName, Model model) {
        model.addAttribute("userList", adminService.userList(nickName));
        return "views/user/index";
    }

    /**
     * 通知连列表
     *
     * @param model
     * @return
     */
    @GetMapping("g")
    public String g(Model model) {
        model.addAttribute("gList", adminService.g());
        return "views/user/g";
    }

    /**
     * 保存通知
     *
     * @return
     */
    @PostMapping("save_g")
    @ResponseBody
    public String saveG(Integer id, String text) {
        return adminService.saveG(id, text);
    }

    /**
     * 编辑
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("up")
    public String upG(Integer id, Model model) {
        model.addAttribute("g", adminService.up(id));
        return "views/user/up";
    }

    /**
     * 通告状态修改
     *
     * @param id
     * @param state
     * @return
     */
    @PostMapping("gState")
    @ResponseBody
    public String gState(int id, int state) {
        System.out.println(state);
        return adminService.satrt(state, id);
    }

    /**
     * 删除通告
     * @param id
     * @return
     */
    @PostMapping("gremoe")
    @ResponseBody
    public String gremoe(int id) {
        return adminService.gremoe(id);
    }
}
