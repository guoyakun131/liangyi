package com.liangyi.controller;

import com.liangyi.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/wxPay")
public class WxPayController {
    @Autowired
    private WxPayService wxPayService;

    @PostMapping("order")
    public Map<String, String> ordre(HttpServletRequest request, String session_id, int order_id) {
        Map<String, String> map = null;
        try {
            map = wxPayService.order(request, session_id, order_id);
            //map.put("isError",true);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            //map.put("isError", false);
            return map;
        }
    }

    @PostMapping("notify")
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        wxPayService.wxNotify(request, response);
    }
}
