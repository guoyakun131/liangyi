package com.liangyi.mapper;

import com.liangyi.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     *
     * @return
     */
    @Select("select * from user")
    List<User> user ();

    /**
     *查询用户是否存在
     * @param openid
     * @return
     */
    @Select("select open_id from user where open_id = #{openid}")
    String selectUser(@Param("openid")String openid);


    /**
     * 添加用户到数据库
     * @param avatar
     * @param nick_name
     * @param open_id
     * @param add_time
     */
    @Insert("INSERT into user (avatar,nick_name,open_id,add_time) VALUES (#{avatar},#{nick_name},#{open_id},#{add_time})")
    void addUser(@Param("avatar")String avatar, @Param("nick_name")String nick_name, @Param("open_id")String open_id, @Param("add_time")int add_time,@Param("add_time")String session_id);

}
