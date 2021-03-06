package com.liangyi.mapper;

import com.liangyi.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsMapper {


    /**
     * 分类查询商品
     * @param type
     * @return
     */
    @Select("SELECT id,img,name,price,paynums from goods where type = #{type} order by id desc")
    List<Goods> queryGoods(@Param("type")int type);

    /**
     * 所有商品列表
     * @return
     */
    @Select("SELECT id,img,name,price,paynums from goods order by id desc")
    List<Goods> queryGoodsList();
    /**
     * 查询所有商品数量
     * @return
     */
    @Select("SELECT COUNT(*) from goods")
    int queryGoodsAll();

    /**
     * 查询类型为“上新”的商品数量
     * @return
     */
    @Select("SELECT COUNT(*) from goods WHERE type = 1")
    int queryGoodsNew();

    /**
     * 查询类型为“热销”的商品数量
     * @return
     */
    @Select("SELECT COUNT(*) from goods WHERE type = 2")
    int queryGoodsHot();

    /**
     * 按id查询商品详情
     * @param id
     * @return
     */
    @Select("SELECT id,name,price,type,express,nums,spec,content,img,tags,add_time as addTime from goods where id = #{id}")
    Goods goodsContent(int id);

    /**
     * 订单确认商品查询
     * @param cartSel
     * @param sessionId
     * @return
     */
    @Select("select g.img,g.name,c.spec,g.price,c.nums,g.express from cart c,goods g,user u WHERE c.user_id = u.id and c.goods_id = g.id and c.id in(${cartSel}) and u.id IN (SELECT id FROM user WHERE sessionkey = #{sessionId})")
    List<Goods> cartListSel(@Param("cartSel")String cartSel, @Param("sessionId")String sessionId);

    /**
     * 提交订单中的商品
     * @param cart_sel
     * @param userId
     * @return
     */
    @Select("SELECT g.id, g.`name`,g.img, g.price, c.nums, c.spec from cart c, goods g WHERE c.user_id = #{userId} and c.goods_id = g.id and c.id in (${cart_sel})")
    List<Goods> orderGoods(@Param("cart_sel") String cart_sel,@Param("userId") int userId);

    /**
     * 后台查询商品列表
     * @return
     */
    @Select("select id,name,price,img,type,express,content,tags,nums,add_time as addTime, spec from goods ORDER BY id desc")
    List<Goods> goodsList();

    /**
     * 后台按商品名字模糊查询
     * @param goodsName
     * @return
     */
    @Select("select id,name,price,img,type,express,content,tags,nums,add_time as addTime, spec from goods where name LIKE '%${goodsName}%' ORDER BY id desc")
    List<Goods> goodsListByName(@Param("goodsName") String goodsName);

    /**
     * 添加商品
     * @param goods
     */
    @Insert("INSERT into goods (name,price,img,type,express,content,tags,nums,add_time,spec) VALUES(#{goods.name},#{goods.price},#{goods.img},#{goods.type},#{goods.express},#{goods.content},#{goods.tags},#{goods.nums},#{goods.addTime},#{goods.spec})")
    void addGoods(@Param("goods") Goods goods);

    /**
     * 编辑商品
     * @param goods
     */
    @Update("update goods set name = #{goods.name}, price = #{goods.price},img = #{goods.img},type = #{goods.type},express = #{goods.express},content = #{goods.content},tags = #{goods.tags},nums = #{goods.nums},spec = #{goods.spec} where id = #{goods.id}")
    void editGoods(@Param("goods") Goods goods);

    /**
     * 编辑商品不换图片
     * @param goods
     */
    @Update("update goods set name = #{goods.name}, price = #{goods.price},type = #{goods.type},express = #{goods.express},content = #{goods.content},tags = #{goods.tags},nums = #{goods.nums},spec = #{goods.spec} where id = #{goods.id}")
    void editGoodsNoImg(@Param("goods") Goods goods);

    /**
     * 删除商品
     * @param id
     */
    @Delete("delete from goods where id = #{id}")
    void delGoods(int id);

    /**
     * 生成订单后更新商品数量
     */
    @Update("UPDATE goods set nums = nums - #{nums} where id = #{id}")
    void goodsNums(@Param("nums") long nums,@Param("id") long id);

    /**
     * 付款人数加1
     * @param id
     */
    @Update("UPDATE goods set paynums = paynums+1 where id = #{id}")
    void goodsPayNums(int id);

    /**
     * 支付过的商品id
     * @param id
     * @return
     */
    @Select("SELECT g.id from `order` o, order_goods og, goods g where o.id = og.order_id and g.id = og.goods_id and `status` != 0 and o.id = #{id}")
    List<Integer> goodsPay(long id);
}
