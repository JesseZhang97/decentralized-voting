/*
 * @Date: 2020-03-15 01:55:53
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-15 03:41:18
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/entity/User.java
 * @Description: 
 */
package com.zz.backend.entity;


public class User {

  private int id;
  private String account;
  private String password;


    public User() {
    }

    public User(int id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}