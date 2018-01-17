package com.liangyi.mapper;

import com.liangyi.entity.Comment;
import com.liangyi.entity.UserComment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    /**
     * 根据商品id查询商品评价
     * @param id
     * @return
     */
    @Select("SELECT g.id, c.content,c.spec,u.avatar,u.nick_name FROM goods g,comment c,`user` u WHERE c.goods_id = g.id and c.user_id = u.id and g.id = #{id}")
    List<UserComment> goodsComment(int id);

    /**
     * 添加评论
     * @param comment
     */
    @Insert("INSERT INTO `comment` (goods_id,order_id,content,user_id,spec,add_time,star) VALUES (#{comment.goodsId},#{comment.orderId},#{comment.content},#{comment.userId},#{comment.spec},#{comment.addTime},#{comment.star})")
    void addGoodsComment(@Param("comment") Comment comment);
}
