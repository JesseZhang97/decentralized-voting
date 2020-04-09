/*
 * @Date: 2020-04-07 01:08:42
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-08 00:19:36
 * 
 * @Description:邮件服务
 */
package com.zz.backend.service.impl;

import java.util.Date;

import com.zz.backend.entity.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class EmailServiceImpl {
  @Autowired
  private JavaMailSenderImpl mailSender;

  /**
   * send email
   */
  public boolean emailSender(Mail mail) {
    try {
      sendMail(mail);
      return true;
    } catch (Exception e) {
      mail.setStatus("fail");
      mail.setError(e.getMessage());
      return false;
    }
  }

  public void sendMail(Mail mail) {
    try {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
      mail.setFrom(getMailSendFrom());
      messageHelper.setFrom(mail.getFrom());// 邮件发件人
      messageHelper.setTo(mail.getTo().split(","));// 邮件收件人
      messageHelper.setSubject(mail.getSubject());// 邮件主题
      messageHelper.setText(mail.getText());// 邮件内容

      if (StringUtils.isEmpty(mail.getSentDate())) {// 发送时间
        mail.setSentDate(new Date());
        messageHelper.setSentDate(mail.getSentDate());
      }
      mailSender.send(messageHelper.getMimeMessage());// 正式发出邮件
      mail.setStatus("ok");
    } catch (Exception e) {
      throw new RuntimeException(e);// 发送失败
    }
  }

  public String getMailSendFrom() {
    return mailSender.getJavaMailProperties().getProperty("from");
  }
}