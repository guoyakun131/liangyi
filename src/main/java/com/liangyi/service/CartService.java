package com.liangyi.service;

import com.liangyi.entity.CartGoods;
import com.liangyi.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return cartMapper.caetList(id);
    }

    /**
     * 加入购物车
     *
     * @param userId
     * @param goods_id
     * @param nums
     * @param spec
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addCart(int userId, int goods_id, String spec, int nums, int add_time) {
        boolean isError;
        try {
            cartMapper.addCart(userId, goods_id, spec, nums, add_time);
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
