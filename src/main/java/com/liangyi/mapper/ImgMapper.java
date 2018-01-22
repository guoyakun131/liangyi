package com.liangyi.mapper;

import com.liangyi.entity.Img;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 删除图片
     * @param id
     */
    @Delete("delete from img where id = #{id}")
    void delImg(int id);
}
