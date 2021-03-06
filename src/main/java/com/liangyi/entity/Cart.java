package com.liangyi.entity;


import org.springframework.context.annotation.Bean;

/**
 * 购物车
 */
public class Cart {

  private long id;
  /**
   *用户ID
   */
  private long userId;
    /**
     *商品ID
     */
  private long goodsId;
    /**
     *规格
     */
  private String spec;

  private long nums;

  private long addTime;


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


  public long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(long goodsId) {
    this.goodsId = goodsId;
  }


  public String getSpec() {
    return spec;
  }

  public void setSpec(String spec) {
    this.spec = spec;
  }


  public long getNums() {
    return nums;
  }

  public void setNums(long nums) {
    this.nums = nums;
  }


  public long getAddTime() {
    return addTime;
  }

  public void setAddTime(long addTime) {
    this.addTime = addTime;
  }

}
