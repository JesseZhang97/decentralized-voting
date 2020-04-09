/*
 * @Date: 2020-04-08 23:30:47
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-09 02:31:50
 * 
 * @Description: 
 */
package com.zz.backend;

import com.zz.backend.service.impl.SetupVotingServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class fullTest {
  @Autowired
  SetupVotingServiceImpl setupService;

  @Test
  public void setUp() throws Exception {
    VoteData vd = new VoteData();
    vd.setCallerPRIVATEKEY(_callerPK);
    vd.setVoteName(_voteName);
    vd.setRegistrationStartTime(_registrationStartTime);
    vd.setRegistrationEndTime(_registrationEndTime);
    vd.setVotingStartTime(_votingStartTime);
    vd.setVotingEndTime(_votingEndTime);
    vd.setVoterAddr(_voterAddress);
    vd.setCandidates(_candidates);

    setupService.getVoteData(vd);
  }
}