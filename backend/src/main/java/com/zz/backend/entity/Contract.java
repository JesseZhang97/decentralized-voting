/*
 * @Date: 2020-04-20 16:19:44
 * @Author: zhen
 * @LastEditTime: 2020-04-21 21:45:02
 * @Description: 
 */
package com.zz.backend.entity;

import java.math.BigInteger;

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
  private BigInteger registrationStartTime;
  // 注册结束时间
  @TableField(value = "registrationEndTime")
  private BigInteger registrationEndTime;
  // 投票开始时间
  @TableField(value = "votingStartTime")
  private BigInteger votingStartTime;
  // 投票结束时间
  @TableField(value = "votingEndTime")
  private BigInteger votingEndTime;
}
