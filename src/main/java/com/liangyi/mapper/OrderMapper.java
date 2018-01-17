package com.liangyi.mapper;

import com.liangyi.entity.Goods;
import com.liangyi.entity.Order;
import com.liangyi.entity.OrderGoods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 订单列表
     *
     * @return
     */
    @Select("SELECT o.id,o.sum,o.des,o.is_sms as isSms,o.express,o.user_id as userId,o.address_id as addressId,o.add_time as addTime,o.order_num as orderNum,o.`status`,o.express_num as expressNum from `order` o WHERE status = #{status} and user_id IN (SELECT id FROM user WHERE sessionkey = #{sessionkey})")
    List<Order> orderList(@Param("status") int status, @Param("sessionkey") String sessionkey);

    /**
     * 所有订单列表
     * @param sessionkey
     * @return
     */
    @Select("SELECT o.id,o.sum,o.des,o.is_sms as isSms,o.express,o.user_id as userId,o.address_id as addressId,o.add_time as addTime,o.order_num as orderNum,o.`status`,o.express_num as expressNum from `order` o WHERE `status` != 6 and user_id IN (SELECT id FROM user WHERE sessionkey = #{sessionkey}) ORDER BY `status`")
    List<Order> orderListAll(@Param("sessionkey") String sessionkey);

    /**
     * 按订单号查询订单中的商品
     * @param id
     * @param orderNum
     * @return
     */
    @Select("SELECT og.id,og.nums,og.goods_name as goodsName,og.spec,og.price,og.goods_id as goodsId,og.order_id as orderId, og.img from `order` o, order_goods og WHERE og.order_id = o.id and o.user_id = #{id} and order_num = #{orderNum}")
    List<OrderGoods> orderGoodsList(@Param("id") int id,@Param("orderNum")String orderNum);

    /**
     * 添加订单
     * @param order
     */
    @Insert("INSERT INTO `order` (sum,des,express,user_id,address_id,add_time,order_num,express_num) VALUES (#{order.sum},#{order.des},#{order.express},#{order.userId},#{order.addressId},#{order.addTime},#{order.orderNum},#{order.expressNum})")
    @Options(useGeneratedKeys = true, keyProperty = "order.id")
    void addOrder(@Param("order") Order order);

    /**
     * 订单操作
     * @param order_id
     * @param userId
     * @param type
     */
    @Update("UPDATE `order` set `status` = #{type} WHERE user_id = #{userId} and id = #{order_id}")
    void changeOrderStatus(@Param("order_id") int order_id,@Param("userId") int userId,@Param("type") int type);

    /**
     * 查询需要支付的订单
     * @param order_id
     * @return
     */
    @Select("select order_num as orderNum, sum from `order` where id = #{order_id}")
    Order orderInfo(int order_id);
}
