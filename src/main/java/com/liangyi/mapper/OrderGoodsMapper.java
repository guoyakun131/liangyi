package com.liangyi.mapper;

import com.liangyi.entity.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
