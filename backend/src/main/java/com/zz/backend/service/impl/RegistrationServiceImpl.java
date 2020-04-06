/*
 * @Date: 2020-04-03 21:22:54
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-06 04:54:13
 * 
 * @Description: 注册阶段
 */
package com.zz.backend.service.impl;

import java.util.ArrayList;

import com.zz.backend.contract.DecVoting;
import com.zz.backend.entity.voteData;
import com.zz.backend.util.EthUtil;

public class RegistrationServiceImpl {

  public void registrationVoter(voteData vd) {
  }

  public void endRegistration() {
    // TODO 计时器,时间unix time,到时调用方法
  }

}

// 注册首先用户要可以知道自己可以参与哪些投票,然后通过这些投票的合约地址调用注册方法