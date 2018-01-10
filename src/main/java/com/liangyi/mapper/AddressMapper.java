package com.liangyi.mapper;

import com.liangyi.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AddressMapper {
    /**
     * 查询用户收货地址
     * @param sessionkey
     * @return
     */
    @Select("SELECT id,NAME,mobile,province,city,dis,detail FROM address WHERE sel = 1 and user_id IN (SELECT id FROM USER WHERE sessionkey = #{sessionkey} )")
    Address findAddress(String sessionkey);
}
