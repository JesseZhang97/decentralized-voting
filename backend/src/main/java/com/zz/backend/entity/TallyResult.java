/*
 * @Date: 2020-04-25 02:18:35
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-25 02:19:44
 * 
 * @Description: 
 */
package com.zz.backend.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter

public class TallyResult {
  private String canditateName;
  private int voteCount;
}