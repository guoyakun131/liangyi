package com.liangyi.mapper;

import com.liangyi.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {


    /**
     * 分类查询商品
     * @param type
     * @return
     */
    @Select("SELECT id,img,name,price from goods where type = #{type} order by id desc")
    List<Goods> queryGoods(@Param("type")int type);

    /**
     * 所有商品列表
     * @return
     */
    @Select("SELECT id,img,name,price from goods order by id desc")
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
    @Select("SELECT id,name,price,express,nums,spec,content,img from goods where id = #{id}")
    Goods goodsContent(int id);
}
