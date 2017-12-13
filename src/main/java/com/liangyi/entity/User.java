package com.liangyi.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * 用户表
 */
public class User {

  private long id;
  /**
   * 头像url
   */
  private String avatar;
  /**
   *昵称
   */
  private String nickName;
  /**
   *微信唯一标识
   */
  private String openId;
  /**
   *创建时间
   */
  private long addTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }


  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }


  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }


  public long getAddTime() {
    return addTime;
  }

  public void setAddTime(long addTime) {
    this.addTime = addTime;
  }

}
