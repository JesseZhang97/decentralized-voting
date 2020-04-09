/*
 * @Date: 2020-03-15 01:55:53
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-04-07 20:10:58
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

    // private static final long serialVersionUID = 1L;
    // 主键
    @TableId(value = "id", type = IdType.AUTO) // IdType.AUTO 主键自增
    private int id;
    // 帐号
    @TableField("account")
    private String account;
    // 登录密码
    @TableField("password")
    private String password;
    // 姓名
    @TableField("name")
    private String name;
    // 公钥
    @TableField("publickey")
    private String publickey;
    // 所属组织
    @TableField("organization")
    private String organization;
    // 邮件地址
    @TableField("email")
    private String email;
    // 认证状态
    @TableField("verified")
    private int verified;

}