package com.liangyi.mapper;

import com.liangyi.entity.Img;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImgMapper {
    /**
     * 根据商品Id查询出商品轮播图
     * @param keyId
     * @return
     */
    @Select("select id,type,key_id as keyId,img from img where key_id = #{keyid}")
    List<Img> imgLsit(int keyId);

    /**
     * 轮播图添加
     * @param key_id
     * @param img
     */
    @Insert("INSERT INTO img (type,key_id,img) VALUES (1,#{key_id},#{img})")
    void editImg(@Param("key_id") int key_id,@Param("img")String img);

    /**
     * 删除图片
     * @param id
     */
    @Delete("delete from img where id = #{id}")
    void delImg(int id);
}
