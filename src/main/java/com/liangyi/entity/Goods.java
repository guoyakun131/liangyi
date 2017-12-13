package com.liangyi.entity;

/**
 * 商品表
 */
public class Goods {

  private long id;
  /**
   *商品名称
   */
  private String name;
  /**
   *价格
   */
  private double price;
  /**
   *商品图片
   */
  private String img;
  /**
   *商品类型 1 上新 2 热销
   */
  private long type;
  /**
   *运费
   */
  private double express;
  /**
   *商品详情
   */
  private String content;
  /**
   *标签
   */
  private String tags;
  /**
   *商品数量
   */
  private long nums;
  /**
   *添加时间
   */
  private long addTime;
  /**
   *规格
   */
  private String spec;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public double getExpress() {
    return express;
  }

  public void setExpress(double express) {
    this.express = express;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
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


  public String getSpec() {
    return spec;
  }

  public void setSpec(String spec) {
    this.spec = spec;
  }

}
