package com.liangyi.service;

import com.liangyi.entity.Cart;
import com.liangyi.entity.CartGoods;
import com.liangyi.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private UserService userService;


    /**
     * 按用户Id查询购物车列表
     *
     * @param session_id
     * @return
     */
    public List<CartGoods> cartList(String session_id) {
        int id = userService.userId(session_id);
        System.out.println("uid" + id);
        return cartMapper.caetList(id);
    }

    /**
     * 我的购物车数量
     * @param session_id
     * @return
     */
    public Map<String, Object> cartCount(String session_id) {
        Map<String, Object> map = new HashMap<>();
        try {
            int cartCount = cartMapper.cartCount(session_id);
            map.put("count", cartCount);
            map.put("isError", true);
            return map;
        } catch (Exception e) {
            map.put("isError", false);
            e.printStackTrace();
            return map;
        }

    }

    /**
     * 加入购物车
     *
     * @param userId
     * @param cart
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addCart(int userId, Cart cart) {
        boolean isError;
        try {
            cartMapper.addCart(userId, cart);
            isError = true;
        } catch (Exception e) {
            isError = false;
            e.printStackTrace();
        }
        return isError;
    }

    /**
     * 删除购物车商品
     *
     * @param cart_id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delCart(int cart_id) {
        boolean isError;
        try {
            cartMapper.delCart(cart_id);
            isError = true;
            System.out.println("删除宝贝成功");
        } catch (Exception e) {
            isError = false;
            e.printStackTrace();
        }
        return isError;
    }

    /**
     * 购物车商品加减
     *
     * @param nums
     * @param cart_id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addNumAndDelNum(int nums, int cart_id) {
        boolean isError;
        try {
            int i = cartMapper.addNumAndDelNum(nums, cart_id);
            if (i == 1) {
                isError = true;
            } else {
                isError = false;
            }
        } catch (Exception e) {
            isError = false;
            System.out.println("失败");
            e.printStackTrace();
        }
        return isError;
    }
}
