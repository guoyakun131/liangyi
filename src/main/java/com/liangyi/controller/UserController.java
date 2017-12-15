package com.liangyi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liangyi.Utils.HttpUtil;
import com.liangyi.config.Config;
import com.liangyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 用户Controller
 */
@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登陆
     * @param request
     * @param response
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) {
        //用户登录凭证（有效期五分钟）。开发者需要在开发者服务器后台调用 api，
        // 使用 code 换取 openid 和 session_key 等信息
        String js_code = request.getParameter("js_code");
        //用户微信昵称
        String nick_name = request.getParameter("nick_name");
        //微信头像地址
        String avatar = request.getParameter("avatar");
        //微信小程序登陆接口url
        String loginUrl = "https://api.weixin.qq.com/sns/jscode2session";

        String reqBody = "appid=" + Config.AppId + "&secret=" + Config.AppSecret + "&js_code=" + js_code + "&grant_type=authorization_code";
        //发送请求得到API返回数据
        String resuslt = HttpUtil.httpRequest(loginUrl, "POST", reqBody);
        //解析返回的json
        ObjectMapper mapper = new ObjectMapper();
        String open_id = null;
        try {
            JsonNode root = mapper.readTree(resuslt);
            JsonNode openid = root.path("openid");
            open_id =openid.textValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userService.addUser(avatar,nick_name,open_id);
    }
}
