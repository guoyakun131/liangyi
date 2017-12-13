package com.liangyi.entity;

/**
 * 管理员表
 */
public class Admin {

  private long id;
  /**
   *用户名
   */
  private String username;
  /**
   *密码
   */
  private String password;
  /**
   *姓名
   */
  private String name;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
