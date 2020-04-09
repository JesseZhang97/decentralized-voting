/*
 * @Date: 2020-04-07 01:31:50
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-08 00:25:34
 * 
 * @Description: 
 */
package com.zz.backend.entity;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Mail {
  private String id;// 邮件id
  private String from;// 邮件发送人
  private String to;// 邮件接收人（多个邮箱则用逗号","隔开）
  private String subject;// 邮件主题
  private String text;// 邮件内容
  private Date sentDate;// 发送时间
  private String status;// 状态
  private String error;// 报错信息
}
