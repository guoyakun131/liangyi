package com.liangyi.mapper;

import com.liangyi.entity.*;
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
    void changeOrderStatus(@Param("order_id") long order_id,@Param("userId") int userId,@Param("type") int type);

    /**
     * 退款成功修改状态
     * @param order_id
     * @param type
     */
    @Update("UPDATE `order` set `status` = #{type} WHERE id = #{order_id}")
    void  tuikuan(@Param("order_id") long order_id,@Param("type") int type);

    /**
     * 查询需要支付的订单
     * @param order_id
     * @return
     */
    @Select("select id, order_num as orderNum, sum from `order` where id = #{order_id}")
    Order orderInfo(int order_id);

    /**
     * 查询订单状态为待付款，待收货等数量
     * @param status
     * @param userid
     * @return
     */
    @Select("SELECT COUNT(*) from `order` where `status` = #{status} and user_id = #{userid}")
    int orderNums(@Param("status") int status,@Param("userid") int userid);

    /**
     * 后台查询所有的订单
     * @return
     */
    @Select("SELECT id,sum,des,is_sms as isSms,express,user_id as userId,address_id as addressId,add_time as addTime,order_num as orderNum,`status`,express_num as expressNum from `order` ORDER BY id desc")
    List<Order> adminOrderListAll();

    /**
     * 后台按照订单号查询
     * @return
     */
    @Select("SELECT id,sum,des,is_sms as isSms,express,user_id as userId,address_id as addressId,add_time as addTime,order_num as orderNum,`status`,express_num as expressNum from `order` where order_num = #{orderNum} ORDER BY id desc")
    List<Order> adminOrderByNum(String orderNum);

    /**
     * 买家信息
     * @param userId
     * @return
     */
    @Select("select id,avatar,nick_name as nickName from user where id = #{userId}")
    User user(int userId);

    /**
     * 订单收货地址
     * @param userId
     * @return
     */
    @Select("select name,mobile,province,city,dis,detail,code from address where sel = 1 and user_id = #{userId}")
    Address a(int userId);

    /**
     * 发货
     */
    @Update("UPDATE `order` set `status` = 2, express_num = #{expressNum}, expCode = #{expCode} WHERE id = #{orderId}")
    void fahuo(@Param("orderId") int orderId,@Param("expressNum")String expressNum,@Param("expCode")String expCode);

    /**
     * 管理员删除订单
     * @param orderId
     */
    @Delete("delete FROM `order` where id = #{orderId}")
    void delOrder(int orderId);

    /**
     * 退款订单查询
     * @param id
     * @return
     */
    @Select("SELECT id,sum,des,express,user_id as userId,order_num as orderNum from `order` where `status` = 7 and id = #{id}")
    Order refundOrder(int id);
}
