/*
 * @Date: 2020-03-16 21:04:40
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-16 21:05:14
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/api/Result.java
 * @Description: 
 */
package com.zz.backend.api;

public class Result {
  private int code;

  public Result(int code) {
      this.code = code;
  }

  public int getCode() {
      return code;
  }

  public void setCode(int code) {
      this.code = code;
  }
}