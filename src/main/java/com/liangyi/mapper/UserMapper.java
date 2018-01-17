package com.liangyi.mapper;

import com.liangyi.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * @return
     */
    @Select("select * from user")
    List<User> user();

    /**
     * 查询用户是否存在
     *
     * @param openid
     * @return
     */
    @Select("select open_id from user where open_id = #{openid}")
    String selectUser(@Param("openid") String openid);


    /**
     * 添加用户到数据库
     *
     * @param avatar
     * @param nick_name
     * @param open_id
     * @param add_time
     */
    @Insert("INSERT into user (avatar,nick_name,open_id,add_time,sessionkey) VALUES (#{avatar},#{nick_name},#{open_id},#{add_time},#{session_id})")
    void addUser(@Param("avatar") String avatar, @Param("nick_name") String nick_name, @Param("open_id") String open_id, @Param("add_time") int add_time, @Param("session_id") String session_id);

    /**
     * 更新用户
     *
     * @param avatar
     * @param nick_name
     * @param session_id
     * @param open_id
     */
    @Update("UPDATE user SET avatar = #{avatar}, nick_name = #{nick_name}, sessionkey = #{session_id} where open_id = #{open_id}")
    void updateUser(@Param("avatar") String avatar, @Param("nick_name") String nick_name, @Param("session_id") String session_id, @Param("open_id") String open_id);

    /**
     * 查询用户Id
     * @param session_id
     * @return
     */
    @Select("SELECT id FROM user WHERE sessionkey = #{session_id}")
    User userId(String session_id);

    /**
     * 查询openId
     * @param session_id
     * @return
     */
    @Select("select open_id from user where sessionkey = #{session_id}")
    String openId(String session_id);
}