package com.liangyi.entity;

import org.springframework.stereotype.Service;

/**
 * 收货地址表
 */
@Service
public class Address {

  private long id;
  /**
   * 用户ID
   */
  private long userId;
  /**
   * 收货人姓名
   */
  private String name;
  /**
   * 手机号
   */
  private String mobile;
  /**
   * 省
   */
  private String province;
  /**
   *市
   */
  private String city;
  /**
   *区
   */
  private String dis;
  /**
   *详细地址
   */
  private String detail;
  /**
   *邮政编码
   */
  private String code;
  /**
   *添加时间
   */
  private long addTime;
  /**
   *0 未选中 1 选中
   */
  private long sel;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }


  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  public String getDis() {
    return dis;
  }

  public void setDis(String dis) {
    this.dis = dis;
  }


  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public long getAddTime() {
    return addTime;
  }

  public void setAddTime(long addTime) {
    this.addTime = addTime;
  }


  public long getSel() {
    return sel;
  }

  public void setSel(long sel) {
    this.sel = sel;
  }

}
