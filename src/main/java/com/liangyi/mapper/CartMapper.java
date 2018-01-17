package com.liangyi.mapper;

import com.liangyi.entity.Cart;
import com.liangyi.entity.CartGoods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    /**
     * 按用户id查询购物车列表
     *
     * @param id
     * @return
     */
    @Select("SELECT g.img, g.price, g.name as goods_name,c.id as cart_id,c.user_id,c.goods_id,c.spec,c.nums,c.add_time FROM cart c,user u, goods g WHERE c.user_id = u.id AND c.goods_id = g.id and u.id = #{id} order by c.add_time desc")
    List<CartGoods> caetList(int id);

    /**
     * 购物车数量
     * @param session_id
     * @return
     */
    @Select("SELECT COUNT(*) from cart WHERE user_id in(SELECT id from user where sessionkey = #{session_id})")
    int cartCount(String session_id);

    /**
     * 加入购物车
     *
     * @param userId
     * @param cart
     */
    @Insert("INSERT INTO cart (user_id,goods_id,spec,nums,add_time) VALUES (#{userId},#{cart.goodsId},#{cart.spec},#{cart.nums},#{cart.addTime})")
    @Options(useGeneratedKeys = true, keyProperty = "cart.id")
    void addCart(@Param("userId") int userId, @Param("cart") Cart cart);

    /**
     * 删除购物车中商品
     *
     * @param id
     */
    @Delete("DELETE FROM cart WHERE id = #{id}")
    void delCart(int id);

    /**
     * 请空生成订单的购物车商品
     */
    @Delete("DELETE FROM cart WHERE id in (${cart_sel})")
    void delOrderCart(@Param("cart_sel") String cart_sel);

    /**
     * 购物车商品数量加减
     *
     * @param nums
     * @param cart_id
     */
    @Delete("UPDATE cart set nums = #{nums} where id = #{id}")
    int addNumAndDelNum(@Param("nums") int nums, @Param("id") int cart_id);

}


