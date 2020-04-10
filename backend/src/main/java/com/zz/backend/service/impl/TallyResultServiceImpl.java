/*
 * @Date: 2020-04-08 21:21:47
 * @Author: zhen
 * @LastEditTime: 2020-04-10 00:18:04
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

import org.springframework.stereotype.Service;

@Service
public class TallyResultServiceImpl {
  public Map<String, Integer> tallyResult(String _contractAddress, String PRIVATE_KEY) throws Exception {
    Map<String, Integer> resultMap = new HashMap<String, Integer>();
    EthUtil.connectEthereum();
    DecVoting voting = EthUtil.loadContract(PRIVATE_KEY, _contractAddress);

    List<String> candidatesList = new ArrayList<>();
    int numOfCanditates = voting.numOfCandidates().send().intValue();
    for (int i = 0; i < numOfCanditates; i++) {
      BigInteger newI = BigInteger.valueOf(i);
      candidatesList.add(voting.candidates(newI).send().toString());
    }

    voting.beginTallyPhase().send();
    for (int i = 0; i < candidatesList.size(); i++) {
      resultMap.put(candidatesList.get(i), voting.electionResults(candidatesList.get(i)).send().intValue());
    }
    return resultMap;
  }
}