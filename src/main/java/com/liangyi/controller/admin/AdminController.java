package com.liangyi.controller.admin;

import com.liangyi.Utils.FileUtil;
import com.liangyi.config.Config;
import com.liangyi.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        System.out.println("username:" + username + "newpw:" + newpw + "newpwc:" + newpwc);
        adminService.changePw(username, newpw, newpwc);
        return "redirect:/admin/Login";
    }

    /**
     * 退款
     *
     * @param id
     * @return
     */
    @PostMapping("refund")
    @ResponseBody()
    public String refund(Integer id) {
        return adminService.refund(id);
    }

    /**
     * 商品详情图片上传
     * @param upfile
     */
    @PostMapping("upload")
    @ResponseBody
    public String upload(@RequestParam("upfile") MultipartFile upfile) {
        String fileName = upfile.getOriginalFilename();
        try {
            if (!upfile.isEmpty()) {
                FileUtil.uploadFile(upfile.getBytes(), Config.filePath, fileName);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String config = "{\"state\": \"SUCCESS\"," +
                "\"url\": \"" +"/image/upload/" + fileName + "\"," +
                "\"title\": \"" + fileName + "\"," +
                "\"original\": \"" + fileName + "\"}";
        return config;
    }
}
