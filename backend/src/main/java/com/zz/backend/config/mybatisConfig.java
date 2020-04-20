/*
 * @Date: 2020-04-20 20:22:28
 * @Author: zhen
 * @LastEditTime: 2020-04-20 20:28:31
 * @Description: 
 */
package com.zz.backend.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class mybatisConfig {
  /**
   * 分页插件
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
  }
}