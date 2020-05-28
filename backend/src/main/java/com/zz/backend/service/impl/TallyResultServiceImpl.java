/*
 * @Date: 2020-04-08 21:21:47
 * @Author: zhen
 * @LastEditTime: 2020-05-12 17:01:41
 * @Description: 
 */
package com.zz.backend.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zz.backend.contract.DecVoting;
import com.zz.backend.entity.TallyResult;
import com.zz.backend.entity.VoteData;
import com.zz.backend.util.EthUtil;

import org.springframework.stereotype.Service;

@Service
public class TallyResultServiceImpl {
  public Map<String, Integer> tallyResult(VoteData vd) throws Exception {
    String _contractAddress = vd.getContractAddress();
    String PRIVATE_KEY = vd.getCallerPRIVATEKEY();
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
      TallyResult tr = new TallyResult();
      tr.setCanditateName(candidatesList.get(i));
      tr.setVoteCount(voting.electionResults(candidatesList.get(i)).send().intValue());
      resultMap.put(candidatesList.get(i), voting.electionResults(candidatesList.get(i)).send().intValue());
    }
    return resultMap;
  }
}