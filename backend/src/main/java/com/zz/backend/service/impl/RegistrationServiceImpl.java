/*
 * @Date: 2020-04-03 21:22:54
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-11 04:08:36
 * 
 * @Description: 注册阶段
 */
package com.zz.backend.service.impl;

import java.math.BigInteger;

import com.zz.backend.contract.DecVoting;
import com.zz.backend.util.EthUtil;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;

@Service
public class RegistrationServiceImpl {

  public boolean registrationVoter(String _contractAddress, String PRIVATE_KEY) throws Exception {
    // load contract get data
    EthUtil.connectEthereum();
    Credentials credentials = EthUtil.credentials(PRIVATE_KEY);
    String voterAddress = credentials.getAddress();
    DecVoting voting = EthUtil.loadContract(PRIVATE_KEY, _contractAddress);
    // 过滤不在投票名单的地址
    boolean inList = false;
    int numOfVoter = voting.numOfVoters().send().intValue();
    for (int i = 0; i < numOfVoter; i++) {
      BigInteger newI = BigInteger.valueOf(i);
      if (voterAddress.equals(voting.voterAddress(newI).send().toString()) == false) {
        inList = false;
      } else {
        inList = true;
        break;
      }
    }
    if (inList == false) {
      return false;
    }
    // 已经注册过的用户不能再次注册
    if (voting.registeredVoters(voterAddress).send() == true) {
      return false;
    }
    boolean ifSuccess = voting.registeredVoters(voterAddress).send();
    EthUtil.closeEthereum();
    return ifSuccess;
  }

  public boolean endRegistration(String _contractAddress, String PRIVATE_KEY) throws Exception {
    // TODO 计时器,时间unix time,到时调用方法 OR 遍历循环mapper后如果全为true调用
    EthUtil.connectEthereum();
    DecVoting voting = EthUtil.loadContract(PRIVATE_KEY, _contractAddress);
    voting.endRegistrationPhase().send();
    if (voting.state().send().intValue() == 2) {
      EthUtil.closeEthereum();
      return true;
    }
    EthUtil.closeEthereum();
    return false;
  }

}