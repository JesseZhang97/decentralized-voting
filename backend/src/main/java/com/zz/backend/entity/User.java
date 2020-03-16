/*
 * @Date: 2020-03-15 01:55:53
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-17 02:42:20
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/entity/User.java
 * @Description: 
 */
package com.zz.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@TableName("user")
public class User {

    private static final long serialVersionUID = 1L;
    //主键
    @TableId(value="id", type = IdType.AUTO) //IdType.AUTO  主键自增
    private int id;
    //帐号
    @TableField("account")
    private String account;
    //登录密码
    @TableField("password")
    private String password;
    //姓名
    @TableField("name")
    private String name;
    //所属组织
    @TableField("organization")
    private String organization;



    public User() {
    }

    public User(int id, String account, String password, String name, String organization) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
        this.organization = organization;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

}