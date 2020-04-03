/*
 * @Date: 2020-04-01 23:02:55
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-03 21:25:58
 * 
 * @Description:用户设定投票内容
 */
package com.zz.backend.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.zz.backend.contract.DecVoting;
import com.zz.backend.entity.voteData;
import com.zz.backend.util.EthUtil;

import org.springframework.stereotype.Service;

@Service
/**
 * @description:处理前端传入的投票信息
 * @param {type}
 * @return:
 */
public class SetupVotingServiceImpl {
  public boolean getVoteData(voteData vd) throws Exception {
    // FIXME 简化获取credentials的次数(现在需要get两次私钥),source code in ETHUtil
    // get caller ADDRESS and DEPLOY
    String deployedContract = EthUtil.deployContract(vd.getCallerPRIVATEKEY());
    // load contract
    DecVoting voting = EthUtil.loadContract(vd.getCallerPRIVATEKEY(), deployedContract);
    // 传入投票参数 传入投票发起人的私钥签名交易（用于部署合约签署交易）
    // 可能需要返回一个ethereumscan链接or something
    String _voteName = vd.getVoteName();
    BigInteger _registrationStartTime = vd.getRegistrationStartTime();
    BigInteger _registrationEndTime = vd.getRegistrationEndTime();
    BigInteger _votingStartTime = vd.getVotingStartTime();
    BigInteger _votingEndTime = vd.getVotingEndTime();
    List<String> _voterAddr = vd.getVoterAddr();
    List<String> _candidates = vd.getCandidates();
    voting.finishSetUp(_voteName, _registrationStartTime, _registrationEndTime, _votingStartTime, _votingEndTime,
        _voterAddr, _candidates);
    return true;
  }
}