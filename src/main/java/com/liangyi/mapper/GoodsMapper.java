package com.liangyi.mapper;

import com.liangyi.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {


    /**
     * 按分页查询商品列表，每次查10条
     * @return
     */
    @Select("SELECT * from goods order by id desc LIMIT #{page_end},#{page_start}")
    List<Goods> queryGoods(@Param("page_end")int page_end,@Param("page_start")int page_start);
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
}
