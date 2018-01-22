package com.liangyi.mapper;

import com.liangyi.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {

    /**
     *登录查询
     * @param name
     * @return
     */
    @Select("select * from admin where username = #{name}")
    Admin adminInfo(String name);

    /**
     * 修改管理员密码
     * @param userName
     * @param password
     */
    @Update("update admin set password = #{password} where username = #{userName}")
    void changePw(@Param("userName") String userName, @Param("password") String password);

}
