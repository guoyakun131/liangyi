package com.liangyi.service.admin;

import com.liangyi.config.Config;
import com.liangyi.entity.Address;
import com.liangyi.entity.Order;
import com.liangyi.entity.OrderGoods;
import com.liangyi.entity.User;
import com.liangyi.mapper.OrderGoodsMapper;
import com.liangyi.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    /**
     * 后台查询订单列表
     *
     * @return
     */
    public List<Order> orderList(String orderNum) {
        if (orderNum == null) {
            return orderMapper.adminOrderListAll();
        } else {
            return orderMapper.adminOrderByNum(orderNum);
        }
    }

    /**
     * 订单中的商品
     *
     * @param id
     * @return
     */
    public List<OrderGoods> orderGoodsList(int id) {
        List<OrderGoods> list = orderGoodsMapper.orderGoods(id);
        for (OrderGoods orderGoods : list) {
            orderGoods.setImg(Config.url + orderGoods.getImg());
        }
        return list;
    }

    /**
     * 订单用户
     * @param userId
     * @return
     */
    public User userInfo(int userId) {
        return orderMapper.user(userId);
    }

    /**
     * 订单收货地址
     * @param userId
     * @return
     */
    public Address address(int userId) {
        Address address = orderGoodsMapper.address(userId);
        return address;
    }

    /**
     * 发货
     * @param orderId
     * @param expressNum
     * @param expCode
     */
    public void fahuo(int orderId,String expressNum,String expCode){
        orderMapper.fahuo(orderId, expressNum, expCode);
    }

    /**
     * 后台删除订单
     * @param orderId
     */
    @Transactional
    public String delOrder(int orderId){
        try{
            orderMapper.delOrder(orderId);
            return "成功";
        }catch (Exception e){
            e.printStackTrace();
            return "失败";
        }

    }
}
