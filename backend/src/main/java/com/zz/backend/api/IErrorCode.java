/*
 * @Date: 2020-03-15 01:50:08
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-15 01:50:19
 * @FilePath: /backend/src/main/java/com/zz/backend/api/IErrorCode.java
 * @Description: 
 */
package com.zz.backend.api;

/**
 * 封装API的错误码
 * Created by macro on 2019/4/19.
 */
public interface IErrorCode {
  long getCode();
  String getMessage();
}