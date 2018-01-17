package com.liangyi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
