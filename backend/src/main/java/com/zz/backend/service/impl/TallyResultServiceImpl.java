/*
 * @Date: 2020-04-08 21:21:47
 * @Author: zhen
 * @LastEditTime: 2020-04-08 23:30:07
 * @Description: 
 */
package com.zz.backend.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zz.backend.contract.DecVoting;
import com.zz.backend.util.EthUtil;

public class TallyResultServiceImpl {
  public void tallyResult(String _contractAddress, String PRIVATE_KEY) throws Exception {
    EthUtil.connectEthereum();
    DecVoting voting = EthUtil.loadContract(PRIVATE_KEY, _contractAddress);
    List<String> candidatesList = new ArrayList<>();// 候选人名单
    // Map<String, int> resultMap = new HashMap<String, int>();
    int numOfCanditates = voting.numOfCandidates().send().intValue();
    for (int i = 0; i < numOfCanditates; i++) {
      BigInteger newI = BigInteger.valueOf(i);
      candidatesList.add(voting.candidates(newI).send().toString());
    }
    voting.beginTallyPhase().send();
    for (int i = 0; i < candidatesList.size(); i++) {
      voting.electionResults(candidatesList.get(i));
    } // FIXME hashmap??
  }
}