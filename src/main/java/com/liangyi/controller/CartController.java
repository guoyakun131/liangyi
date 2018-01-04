package com.liangyi.controller;

import com.liangyi.entity.Cart;
import com.liangyi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 购物车控制器
 */
@RestController
@RequestMapping("/Cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("cart_list")
    public void cartList(HttpServletRequest request){
        String session_id =request.getParameter("session_id");
        System.out.println(session_id);
    }
}
