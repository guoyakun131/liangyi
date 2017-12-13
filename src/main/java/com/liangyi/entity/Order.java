package com.liangyi.entity;


public class Order {

  private long id;
  private double sum;
  private String des;
  private long isSms;
  private double express;
  private long userId;
  private long addressId;
  private long addTime;
  private String orderNum;
  private long status;
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
