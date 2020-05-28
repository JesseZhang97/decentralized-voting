/*
 * @Date: 2020-04-06 19:20:32
 * @Author: zhen
 * @LastEditTime: 2020-05-12 17:00:35
 * @Description: 
 */
package com.zz.backend.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class VoteData {
  private String voteName;
  private String registrationStartTime;
  private String registrationEndTime;
  private String votingStartTime;
  private String votingEndTime;
  // private BigInteger registrationStartTime;
  // private BigInteger registrationEndTime;
  // private BigInteger votingStartTime;
  // private BigInteger votingEndTime;
  // private List<String> voterAddr;
  // private List<String> candidates;
  private String[] voterAddr;
  private String[] candidates;
  /********************************** */
  private String callerPRIVATEKEY;
  private String callerPUBLICKEY;
  private String contractAddress;
  /********************************** */
  private String vote;
}