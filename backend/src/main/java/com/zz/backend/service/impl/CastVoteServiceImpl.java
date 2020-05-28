/*
 * @Date: 2020-04-08 18:19:22
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-05-12 17:00:51
 * 
 * @Description: 
 * 
 * }
 */
package com.zz.backend.service.impl;

import java.math.BigInteger;

import com.zz.backend.contract.DecVoting;
import com.zz.backend.entity.VoteData;
import com.zz.backend.util.EthUtil;
import com.zz.backend.util.UnixTime;

import org.springframework.stereotype.Service;

@Service
public class CastVoteServiceImpl {

  /**
   * 用户给出选票
   * 
   * @param _contractAddress
   * @param PRIVATE_KEY
   * @param vote
   * @return
   * @throws Exception
   */
  public String castVote(VoteData vd) throws Exception {
    String ETHEREUM_ERROR = "ETHEREUM NETWORK ERROR";
    String NOT_REGISTERED_ERROE = "用户没有注册不能投票";
    String ALREADY_VOTED_ERROR = "用户已经投票,请勿重复投票";
    String notTime = "还未到投票时间";
    String timeExpired = "投票时间已过";
    // Credentials credentials = EthUtil.credentials(PRIVATE_KEY);
    String _contractAddress = vd.getContractAddress();
    String PRIVATE_KEY = vd.getCallerPRIVATEKEY();
    String vote = vd.getVote();

    EthUtil.connectEthereum();
    // time validation
    DecVoting voting = EthUtil.loadContract(PRIVATE_KEY, _contractAddress);
    if (UnixTime.getNowTimeStamp().compareTo(voting.votingStartTime().send()) < 0) {
      return notTime;
    }
    if (UnixTime.getNowTimeStamp().compareTo(voting.votingEndTime().send()) > 0) {
      return timeExpired;
    }
    // cast
    String txHash = voting.castVote(vote).send().getTransactionHash();
    if (txHash == null) {
      return ETHEREUM_ERROR;
    } else if (voting.voteSC().send().intValue() == 1) {
      // 没有注册不能投票
      return NOT_REGISTERED_ERROE;
    } else if (voting.voteSC().send().intValue() == 2) {
      // 已经投票过无法再次投票
      return ALREADY_VOTED_ERROR;
    } else {
      return txHash;
    }
  }

  // 读取投票信息
  public VoteData returnVoteData(VoteData vd) throws Exception {
    String _contractAddress = vd.getContractAddress();
    String PRIVATE_KEY = vd.getCallerPRIVATEKEY();
    VoteData vdNEW = new VoteData();
    EthUtil.connectEthereum();
    DecVoting voting = EthUtil.loadContract(PRIVATE_KEY, _contractAddress);
    vdNEW.setVoteName(voting.voteName().send());// 投票名称
    vdNEW.setVotingStartTime(voting.votingStartTime().send().toString());// 投票开始时间
    vdNEW.setVotingEndTime(voting.votingEndTime().send().toString());// 投票结束时间
    // 候选人名单
    int numOfCanditates = voting.numOfCandidates().send().intValue();
    String[] candidatesList = new String[numOfCanditates];
    for (int i = 0; i < numOfCanditates; i++) {
      BigInteger newI = BigInteger.valueOf(i);
      candidatesList[i] = voting.candidates(newI).send().toString();
      // andidatesList.add(voting.candidates(newI).send().toString());
    }
    vdNEW.setCandidates(candidatesList);
    return vdNEW;
  }

  // 结束投票阶段
  public boolean endVoting(VoteData vd) throws Exception {
    String _contractAddress = vd.getContractAddress();
    String PRIVATE_KEY = vd.getCallerPRIVATEKEY();
    EthUtil.connectEthereum();
    DecVoting voting = EthUtil.loadContract(PRIVATE_KEY, _contractAddress);
    if (UnixTime.getNowTimeStamp().compareTo(voting.registrationEndTime().send()) < 0) {
      return false;
    }
    voting.endVotingPhase().send();
    if (voting.state().send().intValue() == 3) {
      return true;
    }
    return false;
  }
}
