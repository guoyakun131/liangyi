package com.liangyi.entity;

/**
 * 订单商品表
 */
public class OrderGoods {

  private long id;
  /**
   * 订单ID
   */
  private long orderId;
  /**
   * 商品ID
   */
  private long goodsId;
  /**
   *商品数量
   */
  private long nums;
  /**
   *商品名称
   */
  private String goodsName;
  /**
   *规格
   */
  private String spec;
  /**
   *单价
   */
  private double price;
  /**
   *添加时间
   */
  private long addTime;
  /**
   *商品图片
   */
  private String img;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(long goodsId) {
    this.goodsId = goodsId;
  }


  public long getNums() {
    return nums;
  }

  public void setNums(long nums) {
    this.nums = nums;
  }


  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }


  public String getSpec() {
    return spec;
  }

  public void setSpec(String spec) {
    this.spec = spec;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public long getAddTime() {
    return addTime;
  }

  public void setAddTime(long addTime) {
    this.addTime = addTime;
  }


  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

}
