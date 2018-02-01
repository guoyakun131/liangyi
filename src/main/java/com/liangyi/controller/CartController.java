package com.liangyi.controller;

import com.liangyi.entity.Cart;
import com.liangyi.entity.CartGoods;
import com.liangyi.entity.Order;
import com.liangyi.service.CartService;
import com.liangyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/Cart")
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
     * 我的购物车数量
     * @param session_id
     * @return
     */
    @PostMapping("cart_count")
    public Map<String,Object> cartCount(String session_id) {
        return cartService.cartCount(session_id);
    }

    /**
     * 加入购物车
     *
     * @return
     */
    @PostMapping("add_cart")
    public Map<String, Object> addCart(Cart cart,String session_id) {
        int userId = userService.userId(session_id);
        //得到时间戳,设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
        // new Date()为获取当前系统时间，也可使用当前时间戳
        String date = df.format(new Date());
        int add_time = Integer.valueOf(date);
        cart.setAddTime(add_time);
        System.out.println(add_time);
        //加入购物车返回成功或者失败
        boolean isError = cartService.addCart(userId,cart);
        System.out.println("id"+cart.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("isError", isError);
        map.put("id", cart.getId());
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
