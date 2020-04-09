/*
 * @Date: 2020-04-03 21:22:54
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-08 19:49:34
 * 
 * @Description: 注册阶段
 */
package com.zz.backend.service.impl;

import java.math.BigInteger;

import com.zz.backend.contract.DecVoting;
import com.zz.backend.util.EthUtil;

import org.web3j.crypto.Credentials;

public class RegistrationServiceImpl {

  public boolean registrationVoter(String _contractAddress, String PRIVATE_KEY) throws Exception {
    // load contract get data
    EthUtil.connectEthereum();
    Credentials credentials = EthUtil.credentials(PRIVATE_KEY);
    String voterAddress = credentials.getEcKeyPair().getPublicKey().toString();
    DecVoting voting = EthUtil.loadContract(PRIVATE_KEY, _contractAddress);
    // 过滤不在投票名单的地址
    boolean inList = false;
    int numOfVoter = voting.numOfVoters().send().intValue();
    for (int i = 0; i < numOfVoter; i++) {
      BigInteger newI = BigInteger.valueOf(i);
      if (!voterAddress.equals(voting.voterAddress(newI).send().toString())) {
        inList = false;
      } else {
        inList = true;
      }
    }
    if (inList == false) {
      return false;
    }
    voting.registerVoter(voterAddress).send();
    EthUtil.closeEthereum();
    return voting.registeredVoters(voterAddress).send();
  }

  public void endRegistration() {
    // TODO 计时器,时间unix time,到时调用方法 OR 遍历循环mapper后如果全为true调用
  }

}