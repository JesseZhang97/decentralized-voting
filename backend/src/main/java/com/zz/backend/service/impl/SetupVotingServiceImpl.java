/*
 * @Date: 2020-04-01 23:02:55
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-06 05:14:04
 * 
 * @Description:用户设定投票内容
 */
package com.zz.backend.service.impl;

import java.math.BigInteger;
import java.util.List;

import com.zz.backend.contract.DecVoting;
import com.zz.backend.entity.voteData;
import com.zz.backend.util.EthUtil;

import org.springframework.stereotype.Service;

@Service
/**
 * @description:处理前端传入的投票信息
 */
public class SetupVotingServiceImpl {
  public String getVoteData(voteData vd) throws Exception {
    String ERROR_msg = "ERROR";
    // get caller ADDRESS and DEPLOY
    String contractAddress = EthUtil.deployContract(vd.getCallerPRIVATEKEY());
    // load contract
    DecVoting voting = EthUtil.loadContract(vd.getCallerPRIVATEKEY(), contractAddress);
    String _voteName = vd.getVoteName();
    BigInteger _registrationStartTime = vd.getRegistrationStartTime();
    BigInteger _registrationEndTime = vd.getRegistrationEndTime();
    BigInteger _votingStartTime = vd.getVotingStartTime();
    BigInteger _votingEndTime = vd.getVotingEndTime();
    List<String> _voterAddr = vd.getVoterAddr();
    List<String> _candidates = vd.getCandidates();
    String txHash = voting.finishSetUp(_voteName, _registrationStartTime, _registrationEndTime, _votingStartTime,
        _votingEndTime, _voterAddr, _candidates).send().getTransactionHash();
    // for(int i=0; i < voting.numOfVoters().,)
    // voting.voterAddress(param0)

    if (!voting.returnbool().send().booleanValue()) {
      return ERROR_msg;
    }
    return txHash;
  }
}