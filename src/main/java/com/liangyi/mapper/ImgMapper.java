package com.liangyi.mapper;

import com.liangyi.entity.Img;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImgMapper {
    /**
     * 根据商品Id查询出商品轮播图
     * @param keyId
     * @return
     */
    @Select("select * from img where key_id = #{keyid}")
    List<Img> imgLsit(int keyId);
}
