/*
 * @Date: 2020-03-15 01:55:53
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-17 02:13:23
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
@TableName("user")
public class User {
   
    private static final long serialVersionUID = 1L;
    //主键
    @Getter
    @Setter
    @TableId(value="id", type = IdType.AUTO)
    private int id;
    //帐号
    @Getter
    @Setter
    @TableField("account")
    private String account;
    //登录密码
    @Getter
    @Setter
    @TableField("password")
    private String password;
    //姓名
    @Getter
    @Setter
    @TableField("name")
    private String name;
    //所属组织
    @Getter
    @Setter
    @TableField("organaztion")
    private String organization;
}