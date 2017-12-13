package com.liangyi.entity;

/**
 * 订单表
 */
public class Order {

  private long id;
  /**
   *总金额
   */
  private double sum;
  /**
   *留言
   */
  private String des;
  /**
   *是否短信通知 0 否 1 是
   */
  private long isSms;
  /**
   *运费
   */
  private double express;
  /**
   *用户ID
   */
  private long userId;
  /**
   *收货地址ID
   */
  private long addressId;
  /**
   *添加时间
   */
  private long addTime;
  /**
   *订单编号
   */
  private String orderNum;
  /**
   *0 待付款 1 待发货 2 待收货 3 待评价 4 已完成 5 取消 6 已删除
   */
  private long status;
  /**
   *运单编号
   */
  private String expressNum;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public double getSum() {
    return sum;
  }

  public void setSum(double sum) {
    this.sum = sum;
  }


  public String getDes() {
    return des;
  }

  public void setDes(String des) {
    this.des = des;
  }


  public long getIsSms() {
    return isSms;
  }

  public void setIsSms(long isSms) {
    this.isSms = isSms;
  }


  public double getExpress() {
    return express;
  }

  public void setExpress(double express) {
    this.express = express;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getAddressId() {
    return addressId;
  }

  public void setAddressId(long addressId) {
    this.addressId = addressId;
  }


  public long getAddTime() {
    return addTime;
  }

  public void setAddTime(long addTime) {
    this.addTime = addTime;
  }


  public String getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public String getExpressNum() {
    return expressNum;
  }

  public void setExpressNum(String expressNum) {
    this.expressNum = expressNum;
  }

}
