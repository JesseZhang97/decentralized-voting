/*
 * @Date: 2020-04-08 18:19:22
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-08 21:45:01
 * 
 * @Description: 
 * 
 * }
 */
package com.zz.backend.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.zz.backend.contract.DecVoting;
import com.zz.backend.entity.VoteData;
import com.zz.backend.util.EthUtil;

public class CastVoteServiceImpl {

  // 给出选票
  public String castVote(String _contractAddress, String PRIVATE_KEY, String vote) throws Exception {
    String ERROR_msg = "VOTE ERROR";
    // Credentials credentials = EthUtil.credentials(PRIVATE_KEY);
    EthUtil.connectEthereum();
    DecVoting voting = EthUtil.loadContract(PRIVATE_KEY, _contractAddress);
    String txHash = voting.castVote(vote).send().getTransactionHash();
    if (txHash == null) {
      return ERROR_msg;
    } else {
      EthUtil.closeEthereum();
      return txHash;
    }
  }

  // 读取投票信息
  public VoteData returnVoteData(String _contractAddress, String PRIVATE_KEY) throws Exception {
    VoteData vd = new VoteData();
    EthUtil.connectEthereum();
    DecVoting voting = EthUtil.loadContract(PRIVATE_KEY, _contractAddress);
    vd.setVoteName(voting.voteName().send());// 投票名称
    vd.setVotingStartTime(voting.votingStartTime().send());// 投票开始时间
    vd.setVotingEndTime(voting.votingEndTime().send());// 投票结束时间

    List<String> candidatesList = new ArrayList<>();// 候选人名单
    int numOfCanditates = voting.numOfCandidates().send().intValue();
    for (int i = 0; i < numOfCanditates; i++) {
      BigInteger newI = BigInteger.valueOf(i);
      candidatesList.add(voting.candidates(newI).send().toString());
    }
    vd.setCandidates(candidatesList);

    return vd;
  }
}
