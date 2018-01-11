package com.liangyi.mapper;

import com.liangyi.entity.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressMapper {
    /**
     * 查询用户收货地址
     * @param sessionkey
     * @return
     */
    @Select("SELECT id,NAME,mobile,province,city,dis,detail FROM address WHERE sel = 1 and user_id IN (SELECT id FROM USER WHERE sessionkey = #{sessionkey} )")
    Address findAddress(String sessionkey);

    /**
     * 收货地址列表
     * @param sessionkey
     * @return
     */
    @Select("SELECT * from address WHERE user_id IN(SELECT id from user WHERE sessionkey = #{sessionkey})")
    List<Address> findAddressList(String sessionkey);

    /**
     * 选择收货地址
     * @param id
     * @param sessionkey
     */
    @Update("UPDATE address set sel = 1 WHERE sel = 0 and id = #{id} and user_id IN(SELECT id FROM user WHERE sessionkey = #{sessionkey})")
    void saveAddrSel(@Param("id") int id,@Param("sessionkey")String sessionkey);

    /**
     * 取消选择地址
     * @param sessionkey
     */
    @Update("UPDATE address set sel = 0 WHERE sel = 1 and user_id IN(SELECT id FROM user WHERE sessionkey = #{sessionkey})")
    void saveAddrCancel(@Param("sessionkey")String sessionkey);

    /**
     * 收货详情
     * @param id
     * @return
     */
    @Select("SELECT id,a.name,a.mobile,a.province,a.city,a.dis,a.detail,a.code FROM address a WHERE id = #{id}")
    Address addrInfo(int id);

    /**
     * 新增收货地址
     * @param address
     */
    @Insert("INSERT INTO address(user_id,name,mobile,province,city,dis,detail,code) VALUES (#{address.userId},#{address.name},#{address.mobile},#{address.province},#{address.city},#{address.dis},#{address.detail},#{address.code})")
    void addAddress(@Param("address")Address address);

    /**
     * 保存修改收货详情
     * @param address
     * @param sessionkey
     */
    @Update("UPDATE address set name = #{address.name},mobile = #{address.mobile}, province = #{address.province}, city = #{address.city}, dis = #{address.dis}, detail = #{address.detail}, code = #{address.code} WHERE id = #{address.id} and user_id IN(SELECT id from user where sessionkey = #{sessionkey})")
    void editAddr(@Param("address") Address address,@Param("sessionkey") String sessionkey);

    /**
     * 删除收货详情
     * @param id
     * @param sessionkey
     */
    @Delete("DELETE FROM address WHERE id = #{id} and user_id IN(SELECT id from user where sessionkey = #{sessionkey})")
    void delAddress(@Param("id") int id,@Param("sessionkey") String sessionkey);
}
