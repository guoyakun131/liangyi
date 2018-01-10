package com.liangyi.controller;

import com.liangyi.entity.CartGoods;
import com.liangyi.service.CartService;
import com.liangyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车控制器
 */
@RestController
@RequestMapping("/Cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    /**
     * 按用户查询购物车列表
     *
     * @param request
     * @return
     */
    @PostMapping("cart_list")
    public Map<String, List<CartGoods>> cartList(HttpServletRequest request) {
        String session_id = request.getParameter("session_id");
        Map<String, List<CartGoods>> map = new HashMap<>();
        map.put("result", cartService.cartList(session_id));
        return map;
    }

    /**
     * 加入购物车
     *
     * @param request
     * @return
     */
    @PostMapping("add_cart")
    public Map<String, Object> addCart(HttpServletRequest request) {
        //得到session_id
        String session_id = request.getParameter("session_id");
        //数量
        int nums = Integer.valueOf(request.getParameter("nums"));
        //商品id
        int goods_id = Integer.valueOf(request.getParameter("goods_id"));
        //商品规格
        String spec = request.getParameter("spec");
        //得到userId
        int userId = userService.userId(session_id);
        //得到时间戳
        SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        int add_time = Integer.valueOf(date);
        System.out.println(add_time);
        //加入购物车返回成功或者失败
        boolean isError = cartService.addCart(userId, goods_id, spec, nums, add_time);
        Map<String, Object> map = new HashMap<>();
        map.put("isError", isError);
        return map;
    }

    /**
     * 按id删除购物车商品
     * @param request
     * @return
     */
    @PostMapping("del_cart")
    public Map<String, Object> delCart(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //得到session_id
        //request.getParameter("session_id");
        //得到cart_id
        int cart_id = Integer.valueOf(request.getParameter("cart_id"));
        System.out.println(cart_id);
        map.put("isError",cartService.delCart(cart_id));
        return map;
    }

    /**
     * 购物车商品加减
     * @param request
     * @return
     */
    @PostMapping("edit_cart")
    public Map<String, Object> editCart(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //得到cart_id
        int cart_id = Integer.valueOf(request.getParameter("cart_id"));
        //得到nums
        int nums = Integer.valueOf(request.getParameter("nums"));
        System.out.println(cart_id);
        if(cartService.addNumAndDelNum(nums,cart_id)){
            map.put("isError",true);
        }else {
            map.put("errorMessage","失败");
        }
        return map;
    }
}
