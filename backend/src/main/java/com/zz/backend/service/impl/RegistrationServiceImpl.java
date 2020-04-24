/*
 * @Date: 2020-04-03 21:22:54
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-24 22:09:58
 * 
 * @Description: 注册阶段
 */
package com.zz.backend.service.impl;

import java.math.BigInteger;

import com.zz.backend.contract.DecVoting;
import com.zz.backend.entity.VoteData;
import com.zz.backend.util.EthUtil;
import com.zz.backend.util.UnixTime;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;

@Service
public class RegistrationServiceImpl {

  public String registrationVoter(VoteData vd) throws Exception {
    String notTime = "还未到注册时间";
    String timeExpired = "注册时间已过";
    String notInList = "用户不在投票白名单中";
    String alreadyRegistered = "用户已注册请勿重新注册";
    // get data
    String _contractAddress = vd.getContractAddress();
    String PRIVATE_KEY = vd.getCallerPRIVATEKEY();
    // load contract get data
    EthUtil.connectEthereum();
    Credentials credentials = EthUtil.credentials(PRIVATE_KEY);
    String voterAddress = credentials.getAddress();
    // System.out.println(voterAddress);
    DecVoting voting = EthUtil.loadContract(PRIVATE_KEY, _contractAddress);
    if (UnixTime.getNowTimeStamp().compareTo(voting.registrationStartTime().send()) < 0) {
      System.out.println(UnixTime.getNowTimeStamp());
      System.out.println(voting.registrationStartTime().send());
      return notTime;
    }
    if (UnixTime.getNowTimeStamp().compareTo(voting.registrationEndTime().send()) > 0) {
      System.out.println(voting.registrationEndTime().send());
      System.out.println(UnixTime.getNowTimeStamp());
      return timeExpired;
    }
    // 过滤不在投票名单的地址
    int numOfVoter = voting.numOfVoters().send().intValue();
    boolean flag = false;
    for (int i = 0; i < numOfVoter; i++) {
      BigInteger newI = BigInteger.valueOf(i);
      if (voterAddress.equals(voting.voterAddress(newI).send().toString()) == true) {
        flag = true;
        break;
      } else {
        flag = false;
      }
      if (i == numOfVoter - 1) {
        if (flag == false) {
          return notInList;
        }
      }
    }
    if (voting.registeredVoters(voterAddress).send() == true) {
      // 已经注册过的用户不能再次注册
      return alreadyRegistered;
    }
    // if (voterAddress.equals(voting.voterAddress(newI).send().toString()) ==
    // false) {
    // return notInList;
    // } else if (voting.registeredVoters(voterAddress).send() == true) {
    // // 已经注册过的用户不能再次注册
    // return alreadyRegistered;
    // }
    String registerTX = voting.registerVoter(voterAddress).send().getTransactionHash();
    return registerTX;
  }

  public boolean endRegistration(VoteData vd) throws Exception {
    // TODO 计时器,时间unix time,到时调用方法 OR 遍历循环mapper后如果全为true调用
    String _contractAddress = vd.getContractAddress();
    String PRIVATE_KEY = vd.getCallerPRIVATEKEY();
    EthUtil.connectEthereum();
    DecVoting voting = EthUtil.loadContract(PRIVATE_KEY, _contractAddress);
    if (UnixTime.getNowTimeStamp().compareTo(voting.registrationEndTime().send()) < 0) {
      // System.out.println(UnixTime.getNowTimeStamp());
      // System.out.println(voting.registrationEndTime().send());
      return false;
    }
    voting.endRegistrationPhase().send();
    // System.out.println(voting.state().send().intValue());
    if (voting.state().send().intValue() == 2) {
      return true;
    }
    return false;
  }

}