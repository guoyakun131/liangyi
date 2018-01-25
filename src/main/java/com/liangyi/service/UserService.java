package com.liangyi.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liangyi.Utils.KdniaoTrackQueryAPI;
import com.liangyi.entity.*;
import com.liangyi.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Autowired
    private KdniaoTrackQueryAPI kdniaoTrackQueryAPI;

    public List<User> user() {
        return userMapper.user();
    }

    /**
     * 查询用户是否存在
     *
     * @param open_id
     * @return
     */
    public boolean isUser(String open_id) {
        //查询用户是否存在
        String userOpenid = userMapper.selectUser(open_id);
        System.out.println("userOpenid" + userOpenid);
        if (userOpenid == null) {
            return false;
        }
        return true;
    }

    /**
     * 添加用户信息
     *
     * @param avatar
     * @param nick_name
     * @param open_id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(String avatar, String nick_name, String open_id, String session_id) {
        //系统时间
        Date d = new Date();
        //格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        //转int
        int add_time = Integer.valueOf(sdf.format(d).toString());
        //添加到数据库
        userMapper.addUser(avatar, nick_name, open_id, add_time, session_id);
    }

    /**
     * 更新用户信息
     *
     * @param avatar
     * @param nick_name
     * @param session_id
     * @param open_id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(String avatar, String nick_name, String session_id, String open_id) {
        userMapper.updateUser(avatar, nick_name, session_id, open_id);
    }

    /**
     * 查询用户Id
     *
     * @param session_id
     * @return
     */
    public int userId(String session_id) {
        User user = userMapper.userId(session_id);
        return user.getId();
    }

    /**
     * 选择收货地址
     *
     * @param session_id
     * @param addr_id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveAddrSel(int addr_id, String session_id) {
        try {
            //先取消原来的选择
            addressMapper.saveAddrCancel(session_id);
            //从新选择
            addressMapper.saveAddrSel(addr_id, session_id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 订单列表
     *
     * @param status
     * @param session_id
     * @return
     */
    public Map<String, Object> orderList(int status, String session_id) {
        System.out.println(status);
        Map<String, Object> map = new HashMap<>();
        try {
            if (status == 0) {
                //得到userID
                User userId = userMapper.userId(session_id);

                //查询全部订单
                List<Order> orderList = orderMapper.orderListAll(session_id);
                for (Order order : orderList) {
                    //按订单号查询订单中的商品
                    List<OrderGoods> orderGoodsList = orderMapper.orderGoodsList(userId.getId(), order.getOrderNum());
                    order.setOrderGoodsList(orderGoodsList);
                }
                map.put("result", orderList);
            } else {
                //得到userID
                User userId = userMapper.userId(session_id);

                //按状态查询订单列表
                List<Order> orderList = orderMapper.orderList(status - 1, session_id);
                for (Order order : orderList) {
                    //按订单号查询订单中的商品
                    List<OrderGoods> orderGoodsList = orderMapper.orderGoodsList(userId.getId(), order.getOrderNum());
                    order.setOrderGoodsList(orderGoodsList);
                }
                map.put("result", orderList);
            }
            map.put("isError", true);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("isError", false);
            return map;
        }
    }

    /**
     * 订单操作
     *
     * @param session_id
     * @param type
     * @param order_id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> changeOrderStatus(String session_id, int type, int order_id) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = userMapper.userId(session_id);
            if (type == 0 ) {
                orderMapper.changeOrderStatus(order_id, user.getId(), 5);
                map.put("status", 5);
            }else if(type == 1){
                orderMapper.changeOrderStatus(order_id, user.getId(), 7);
                map.put("status", 7);
            }else if (type == 2) {
                orderMapper.changeOrderStatus(order_id, user.getId(), 3);
                map.put("status", 3);
            } else if (type == 3) {
                orderMapper.changeOrderStatus(order_id, user.getId(), 6);
                map.put("status", 6);
            }
            map.put("isError", true);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("isError", false);
            return map;
        }

    }

    /**
     * 各状态的订单数量
     *
     * @param session_id
     * @return
     */
    public List orderNums(String session_id) {
        User userId = userMapper.userId(session_id);
        List orderNumsList = new ArrayList();
        try {
            for (int i = 0; i <= 3; i++) {
                int orderNums = orderMapper.orderNums(i, userId.getId());
                orderNumsList.add(orderNums);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderNumsList;
    }

    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    public boolean addGoodsComment(Comment comment, String session_id) {
        try {

            comment.setGoodsId(orderGoodsMapper.goodsId((int) comment.getGoodsId()));
            User user = userMapper.userId(session_id);
            comment.setUserId(user.getId());
            commentMapper.addGoodsComment(comment);
            orderMapper.changeOrderStatus((int) comment.getOrderId(), (int) comment.getUserId(), 4);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 物流查询
     *
     * @param session_id
     * @param order_id
     * @return
     */
    public String expressInfo(String session_id, int order_id) {
        System.out.println("order_id:" + order_id);
        User user = userMapper.userId(session_id);
        System.out.println(user.getId());
        Map<String, String> exp = userMapper.expressNum(order_id, user.getId());
        System.out.println("expCode:" + exp.get("expCode"));
        try {
            String result = kdniaoTrackQueryAPI.getOrderTracesByJson(exp.get("expCode"), exp.get("express_num"));
//            //解析返回的json
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode root = mapper.readTree(result);
//            String LogisticCode = root.path("LogisticCode").textValue();
//            String Reason = root.path("Reason").textValue();
//            String Traces = root.path("Traces").textValue();
//            map.put("LogisticCode", LogisticCode);
//            map.put("Traces", Traces);
//            System.out.println("LogisticCode:"+LogisticCode+"Traces:"+Traces);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "查询失败";
        }
    }

}
