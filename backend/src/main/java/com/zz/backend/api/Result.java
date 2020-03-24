/*
 * @Date: 2020-03-16 21:04:40
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-03-24 01:27:03
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/api/Result.java
 * @Description: 
 */
package com.zz.backend.api;

public class Result {
    private int code;
    private String data;
    private String msg;

    public Result() {
    }

    public Result(int code, String data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Result(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}