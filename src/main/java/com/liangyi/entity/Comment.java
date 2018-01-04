package com.liangyi.entity;

/**
 * 商品评价表
 */
public class Comment {

  private long id;
  /**
   * 商品ID
   */
  private long goodsId;
  /**
   *订单ID
   */
  private long orderId;
  /**
   *评论内容
   */
  private String content;
  /**
   *用户ID
   */
  private long userId;
  /**
   *商品规格
   */
  private String spec;
  /**
   *添加时间
   */
  private long addTime;
  /**
   *评分 0-5
   */
  private long star;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(long goodsId) {
    this.goodsId = goodsId;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getSpec() {
    return spec;
  }

  public void setSpec(String spec) {
    this.spec = spec;
  }


  public long getAddTime() {
    return addTime;
  }

  public void setAddTime(long addTime) {
    this.addTime = addTime;
  }


  public long getStar() {
    return star;
  }

  public void setStar(long star) {
    this.star = star;
  }

}
