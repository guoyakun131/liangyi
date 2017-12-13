package com.liangyi.entity;

/**
 * 图片表
 */
public class Img {

  private long id;
  /**
   * 图片类型 1 商品图片 2 评论图片
   */
  private long type;
  /**
   * 图片类型ID
   */
  private long keyId;
  /**
   *图片url
   */
  private String img;
  /**
   * 添加时间
   */
  private long addTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public long getKeyId() {
    return keyId;
  }

  public void setKeyId(long keyId) {
    this.keyId = keyId;
  }


  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }


  public long getAddTime() {
    return addTime;
  }

  public void setAddTime(long addTime) {
    this.addTime = addTime;
  }

}
