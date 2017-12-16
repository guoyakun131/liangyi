package com.liangyi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liangyi.Utils.HttpUtil;
import com.liangyi.config.Config;
import com.liangyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

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
     *  @param request
     * @param response
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        System.out.println("login:" + resuslt);
        Map<String, Object> map = new HashMap<>();
        //解析返回的json
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(resuslt);
        String ermsg = root.path("errmsg").textValue();
        String errcode = root.path("errcode").textValue();
        if (ermsg == null && errcode == null) {
            String open_id = root.path("openid").textValue();
            String session_id = root.path("session_key").textValue();
            //判断用户是否存在
            if (!userService.isUser(open_id)) {
                //添加用户
                userService.addUser(avatar, nick_name, open_id);
            }
            map.put("isError",true);
            map.put("session_id",session_id);
            return  map;
        }
        map.put("isError",false);
        map.put("errcode",errcode);
        map.put("errorMessage",ermsg);
        return  map;
    }
}
