/*
 * @Date: 2020-04-08 18:19:22
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-19 00:22:30
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

import org.springframework.stereotype.Service;

@Service
public class CastVoteServiceImpl {

  // 给出选票
  public String castVote(String _contractAddress, String PRIVATE_KEY, String vote) throws Exception {
    String ETHEREUM_ERROR = "ETHEREUM NETWORK ERROR";
    String NOT_REGISTERED_ERROE = "USER NOT REGISTERED";
    String ALREADY_VOTED_ERROR = "USER ALREADY VOTED";
    // Credentials credentials = EthUtil.credentials(PRIVATE_KEY);
    EthUtil.connectEthereum();
    DecVoting voting = EthUtil.loadContract(PRIVATE_KEY, _contractAddress);
    String txHash = voting.castVote(vote).send().getTransactionHash();
    // FIXME 没有注册不能投票 已经投票过无法再次投票
    if (txHash == null) {
      EthUtil.closeEthereum();
      return ETHEREUM_ERROR;
    } else if (voting.voteSC().send().intValue() == 1) {
      // 没有注册不能投票
      EthUtil.closeEthereum();
      return NOT_REGISTERED_ERROE;
    } else if (voting.voteSC().send().intValue() == 2) {
      // 已经投票过无法再次投票
      EthUtil.closeEthereum();
      return ALREADY_VOTED_ERROR;
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
    // FIXME bigint and string problem
    vd.setVotingStartTime(voting.votingStartTime().send().toString());// 投票开始时间
    vd.setVotingEndTime(voting.votingEndTime().send().toString());// 投票结束时间

    List<String> candidatesList = new ArrayList<>();// 候选人名单
    int numOfCanditates = voting.numOfCandidates().send().intValue();
    for (int i = 0; i < numOfCanditates; i++) {
      BigInteger newI = BigInteger.valueOf(i);
      candidatesList.add(voting.candidates(newI).send().toString());
    }
    // vd.setCandidates(candidatesList);
    return vd;
  }

  // 结束投票阶段
  public boolean endVoting(String _contractAddress, String PRIVATE_KEY) throws Exception {
    // TODO 计时器,时间unix time,到时调用方法 OR 遍历循环mapper后如果全为true调用
    EthUtil.connectEthereum();
    DecVoting voting = EthUtil.loadContract(PRIVATE_KEY, _contractAddress);
    voting.endVotingPhase().send();
    if (voting.state().send().intValue() == 3) {
      EthUtil.closeEthereum();
      return true;
    }
    EthUtil.closeEthereum();
    return false;
  }
}
