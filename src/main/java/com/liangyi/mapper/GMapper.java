package com.liangyi.mapper;

import com.liangyi.entity.G;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *
 */
@Mapper
public interface GMapper {

    /**
     * 查询首页通告
     * @return
     */
    @Select("select text from g where start = 1")
    List<String> g();

    /**
     * 通知列表
     * @return
     */
    @Select("select * from g")
    List<G> gList();

    /**
     * 通知添加
     * @param text
     */
    @Insert("insert INTO g (text,start) VALUES (#{text},0)")
    void saveG(String text);

    /**
     * 修改通告
     * @param text
     * @param id
     */
    @Update("UPDATE g SET text = #{text} where id = #{id}")
    void upG(@Param("text") String text,@Param("id") int id);

    /**
     * 编辑查询
     * @param id
     * @return
     */
    @Select("select * from g where id = #{id}")
    G up(int id);

    /**
     * 通告状态修改
     * @param start
     * @param id
     */
    @Select("UPDATE g set `start` = #{start} where id = #{id}")
    void gStart(@Param("start") int start,@Param("id") int id);

    /**
     * 删除通告
     * @param id
     */
    @Delete("delete from g where id = #{id}")
    void gremoe(int id);
}
