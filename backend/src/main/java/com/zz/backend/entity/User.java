/*
 * @Date: 2020-03-15 01:55:53
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-16 20:24:40
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/entity/User.java
 * @Description: 
 */
package com.zz.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

@Data
@TableName("user")
public class User {
    private static final long serialVersionUID = 1L;
    //主键
    @TableId(value="id", type = IdType.AUTO)
    private int id;
    //帐号
    @TableField("account")
    private String account;
    //登录密码
    @TableField("password")
    private String password;

    // @Override
	// protected Serializable pkVal() {
	// 	return this.id;
    // }
    
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