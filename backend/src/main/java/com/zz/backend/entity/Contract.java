/*
 * @Date: 2020-04-20 16:19:44
 * @Author: zhen
 * @LastEditTime: 2020-04-20 16:30:08
 * @Description: 
 */
package com.zz.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@TableName("contract")

public class Contract {
  // 主键 投票合约地址
  @TableId(value = "address")
  private String address;
  // 合约拥有者即合约发起人
  @TableField(value = "owner")
  private String owner;
  // 注册开始时间
  @TableField(value = "registrationStartTime")
  private String registrationStartTime;
  // 注册结束时间
  @TableField(value = "registrationEndTime")
  private String registrationEndTime;
  // 投票开始时间
  @TableField(value = "votingStartTime")
  private String votingStartTime;
  // 投票结束时间
  @TableField(value = "votingEndTime")
  private String votingEndTime;
}
