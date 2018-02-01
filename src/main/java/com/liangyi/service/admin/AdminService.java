package com.liangyi.service.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wxpay.sdk.WXPay;
import com.liangyi.config.WxConfig;
import com.liangyi.entity.G;
import com.liangyi.entity.Order;
import com.liangyi.entity.User;
import com.liangyi.mapper.AdminMapper;
import com.liangyi.mapper.GMapper;
import com.liangyi.mapper.OrderMapper;
import com.liangyi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.ls.LSInput;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GMapper gMapper;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 修改管理员密码
     *
     * @param userName
     * @param newpw
     * @param newpwc
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean changePw(String userName, String newpw, String newpwc) {
        if (newpw != null && newpw.equals(newpwc)) {
            adminMapper.changePw(userName, newpw);
            System.out.println(111);
            return true;
        }
        return false;
    }

    /**
     * 用户列表
     *
     * @return
     */
    public List<User> userList(String nickName) {
        if (nickName == null) {
            return userMapper.userList();
        } else {
            return userMapper.userLsitByName(nickName);
        }
    }

    /**
     * 通知栏列表
     *
     * @return
     */
    public List<G> g() {
        return gMapper.gList();
    }

    /**
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String saveG(Integer id, String text) {
        if (id == null) {
            gMapper.saveG(text);
            return "添加成功";
        } else {
            gMapper.upG(text, id);
            return "修改成功";
        }
    }

    /**
     * 编辑查询
     *
     * @param id
     * @return
     */
    public G up(int id) {
        return gMapper.up(id);
    }

    /**
     * 通告状态修改
     *
     * @param start
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String satrt(int start, int id) {
        try {
            gMapper.gStart(start, id);
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    /**
     * 删除通知栏
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String gremoe(int id) {
        try {
            gMapper.gremoe(id);
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    /**
     * 退款
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String refund(int id) {
        Order order = orderMapper.refundOrder(id);
        double d = order.getSum() * 100;
        int i = (new Double(d)).intValue();
        String sum = String.valueOf(i);
        WxConfig wxConfig = new WxConfig();
        WXPay wxPay = new WXPay(wxConfig);
        Map<String, String> reqData = new HashMap<>();
        //订单号
        reqData.put("out_trade_no", order.getOrderNum());
        //退款单号
        reqData.put("out_refund_no", order.getOrderNum());
        //订单金额
        reqData.put("total_fee", sum);
        //退款金额
        reqData.put("refund_fee", sum);
        System.out.println(reqData);
        Map<String, String> resp = null;
        try {
            resp = wxPay.refund(reqData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("resp:" + resp);
        if (resp.get("err_code_des") != null) {
            System.out.println(resp.get("err_code_des"));
            return resp.get("err_code_des");
        }else {
            //退款成功修改订单为已退款
            orderMapper.tuikuan(order.getId(),8);
            return "退款成功";
        }

    }

}
