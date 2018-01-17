package com.liangyi.service;

import com.liangyi.Utils.TestRandom;
import com.liangyi.entity.*;
import com.liangyi.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ImgMapper imgMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Autowired
    private CartMapper cartMapper;

    /**
     * 查询首页Tab显示的数字
     *
     * @return
     */
    public Map<String, Map<String, Integer>> getNums() {
        Map<String, Map<String, Integer>> maps = new HashMap<>();
        Map<String, Integer> map = new HashMap<>();
        //查询所有商品数量给首页“Tab全部”显示
        map.put("all", goodsMapper.queryGoodsAll());
        //查询上新商品数量给首页“Tab上新”显示
        map.put("new", goodsMapper.queryGoodsNew());
        //查询热销商品数量给首页“Tab热销”显示
        map.put("hot", goodsMapper.queryGoodsHot());
        maps.put("result", map);
        return maps;
    }

    /**
     * 按分类查询商品
     *
     * @param type
     * @return
     */
    public List<Goods> goodsList(int type) {
        return goodsMapper.queryGoods(type);
    }

    /**
     * 全部商品查询
     *
     * @return
     */
    public List<Goods> goodsList() {
        return goodsMapper.queryGoodsList();
    }

    /**
     * 按id查询商品详情
     *
     * @param id
     * @return
     */
    public Map<String, Goods> goodsContent(int id) {
        Map<String, Goods> goodsMap = new HashMap<String, Goods>();
        //查询出商品详情
        Goods goods = goodsMapper.goodsContent(id);
        //查询出商品对应的轮播图
        goods.setImgs(imgMapper.imgLsit(id));
        //放入Map
        goodsMap.put("result", goods);
        return goodsMap;
    }

    /**
     * 查询商品评论
     *
     * @param id
     * @return
     */
    public Map<String, List<UserComment>> goodsComment(int id) {
        Map<String, List<UserComment>> map = new HashMap<>();
        map.put("result", commentMapper.goodsComment(id));
        return map;
    }

    /**
     * 订单查询收货地址
     *
     * @param sessionId
     * @return
     */
    public Address addrSel(String sessionId) {
        return addressMapper.findAddress(sessionId);
    }

    /**
     * 收货地址列表
     *
     * @param sessionId
     * @return
     */
    public Map<String, Object> findAddressList(String sessionId) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Address> addressList = addressMapper.findAddressList(sessionId);
            map.put("result", addressList);
            map.put("isError", true);
        } catch (Exception e) {
            map.put("isError", false);
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 订单确认商品查询
     *
     * @param cartSel
     * @param sessionId
     * @return
     */
    public List<Goods> cartListSel(String cartSel, String sessionId) {
        return goodsMapper.cartListSel(cartSel, sessionId);
    }

    /**
     * 收货详情
     *
     * @param id
     * @return
     */
    public Map<String, Object> addrInfo(int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Address addressInfo = addressMapper.addrInfo(id);
            map.put("result", addressInfo);
            map.put("isError", true);
        } catch (Exception e) {
            map.put("isError", false);
            e.printStackTrace();
        }
        ;
        return map;
    }

    /**
     * 保存修改收货详情
     *
     * @param address
     * @param sessionkey
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean editAddr(Address address, String sessionkey) {
        try {
            addressMapper.editAddr(address, sessionkey);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除收货详情
     *
     * @param id
     * @param sessionkey
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delAddress(int id, String sessionkey) {
        try {
            addressMapper.delAddress(id, sessionkey);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加收货详情
     *
     * @param sessionkey
     * @param address
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addAddress(String sessionkey, Address address) {
        try {
            address.setUserId(userMapper.userId(sessionkey).getId());
            System.out.println(address.getUserId());
            addressMapper.addAddress(address);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 生成订单
     * @param order
     * @param session_id
     * @param cart_sel
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addOrder(Order order, String session_id, String cart_sel) {
        try {
            //UserID
            int userId = userMapper.userId(session_id).getId();
            //查询出提交订单的商品详情
            List<Goods> orderGoodsList = goodsMapper.orderGoods(cart_sel, userId);
            double orderPrice = 0.0;
            for (Goods orderGoods : orderGoodsList) {
                orderPrice += orderGoods.getPrice()*orderGoods.getNums();
            }
            //系统时间
            Date date = new Date();
            //格式化
            SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
            //转int
            long add_time = Long.valueOf(sdf.format(date).toString());
            order.setUserId(userId);
            order.setSum(orderPrice);
            order.setAddTime(add_time);
            //生成订单号
            SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            order.setOrderNum(s.format(date).toString());
            //生成运单号
           // order.setExpressNum("400" + add_time);
            //添加订单
            orderMapper.addOrder(order);

            System.out.println("order" + order.getId());
            //添加订单商品
            for (Goods goods : orderGoodsList) {
                goods.setAddTime(add_time);
                orderGoodsMapper.addOrderGoods(goods, order.getId());
            }
            //清空生成订单的购物车
            cartMapper.delOrderCart(cart_sel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}