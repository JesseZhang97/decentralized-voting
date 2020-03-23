/*
 * @Date: 2020-03-16 21:04:40
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-21 02:45:54
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/api/Result.java
 * @Description: 
 */
package com.zz.backend.api;

public class Result {
    private int code;
    private int id;
    private String msg;


    public Result() {
    }

    public Result(int code, int id, String msg) {
        this.code = code;
        this.id = id;
        this.msg = msg;
    }
    public Result(int code ) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}