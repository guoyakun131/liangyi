package com.liangyi.mapper;

import com.liangyi.entity.Address;
import com.liangyi.entity.Goods;
import com.liangyi.entity.OrderGoods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderGoodsMapper {

    /**
     * 添加订单中的商品
     * @param goods
     * @param orderId
     */
    @Insert("INSERT INTO order_goods (order_id,goods_id,nums,goods_name,spec,price,add_time,img) VALUES (#{orderId},#{goods.id},#{goods.nums},#{goods.name},#{goods.spec},#{goods.price},#{goods.addTime},#{goods.img})")
    void addOrderGoods(@Param("goods") Goods goods, @Param("orderId") long orderId);

    /**
     * 查询商品id
     * @param id
     * @return
     */
    @Select("select goods_id as goodsId from order_goods where id = #{id}")
    int goodsId(int id);

    /**
     *
     * @param id
     * @return
     */
    @Select("SELECT id,order_id as orderId,goods_id as goodsId,nums,goods_name as goodsName,spec,price,img FROM order_goods WHERE order_id = #{id}")
    List<OrderGoods> orderGoods(int id);

    /**
     * 订单收货地址
     * @param userId
     * @return
     */
    @Select("select name,mobile,province,city,dis,detail,code from address where sel = 1 and user_id = #{userId}")
    Address address(@Param("userId") int userId);
}
