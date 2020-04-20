/*
 * @Date: 2020-04-08 23:30:47
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-19 00:21:54
 * 
 * @Description: 
 */
package com.zz.backend;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.zz.backend.entity.VoteData;
import com.zz.backend.service.impl.CastVoteServiceImpl;
import com.zz.backend.service.impl.RegistrationServiceImpl;
import com.zz.backend.service.impl.SetupVotingServiceImpl;
import com.zz.backend.service.impl.TallyResultServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class fullTest {
  @Autowired
  SetupVotingServiceImpl setUpService;

  @Test
  public void setUp() throws Exception {
    String _voteName = "竞选小区业主代表";
    BigInteger _registrationStartTime = new BigInteger("1586031900");
    BigInteger _registrationEndTime = new BigInteger("1586031960");
    BigInteger _votingStartTime = new BigInteger("1586032020");
    BigInteger _votingEndTime = new BigInteger("1586032080");
    List<String> _voterAddress = new ArrayList<>();
    _voterAddress.add("0x1fE341389404cE4de4bED3239A44C91123Fc602C");
    _voterAddress.add("0x75276aAc8709A29743A5Cf2237BB93eC1f9Ab7a9");
    // _voterAddress.add("0x850757399DEb8D3838C74B62935139a51f3A29d2");
    List<String> _candidates = new ArrayList<>();
    _candidates.add("张祯");
    _candidates.add("张三");
    _candidates.add("Mark");
    _candidates.add("Jesse");
    String _callerPK = "0x1d6c2a391786e3f9f6f96d55eac91a1b70875f848e982393ed6ad2fd093c4b3e";
    /*******************************/
    VoteData vd = new VoteData();
    vd.setCallerPRIVATEKEY(_callerPK);
    vd.setVoteName(_voteName);
    // FIXME bigint and string problem
    // vd.setRegistrationStartTime(_registrationStartTime);
    // vd.setRegistrationEndTime(_registrationEndTime);
    // vd.setVotingStartTime(_votingStartTime);
    // vd.setVotingEndTime(_votingEndTime);
    // vd.setVoterAddr(_voterAddress);
    // vd.setCandidates(_candidates);
    String contractAddress = setUpService.getVoteData(vd);
    if (null != contractAddress) {
      System.out.println(contractAddress);
    } else {
      System.out.println("投票合约创建失败");
    }
  }

  @Autowired
  RegistrationServiceImpl registrationService;

  @Test
  public void registrationVoter1() throws Exception {
    String _contractAddress = "0x2c28d08d9f0628810fc3c03433b58106f42b9d76";
    String PRIVATE_KEY = "0x019c25498e5e32e022b894f08151416f45551281266787c14d7ab0e9012ed8af";
    System.out.println(registrationService.registrationVoter(_contractAddress, PRIVATE_KEY));
  }

  @Test
  public void registrationVoter2() throws Exception {
    String _contractAddress = "0x2c28d08d9f0628810fc3c03433b58106f42b9d76";
    String PRIVATE_KEY = "0x83e686c5b57dcedf30caa30299b7027086e045d7e252fa730332ed046c032b72";
    System.out.println(registrationService.registrationVoter(_contractAddress, PRIVATE_KEY));
  }

  @Test
  public void endRegistration() throws Exception {
    String _contractAddress = "0x2c28d08d9f0628810fc3c03433b58106f42b9d76";
    String PRIVATE_KEY = "0x1d6c2a391786e3f9f6f96d55eac91a1b70875f848e982393ed6ad2fd093c4b3e";
    System.out.println(registrationService.endRegistration(_contractAddress, PRIVATE_KEY));
  }

  @Autowired
  CastVoteServiceImpl castVoteService;

  @Test
  public void displayVoteInfo() throws Exception {
    String _contractAddress = "0x2c28d08d9f0628810fc3c03433b58106f42b9d76";
    String PRIVATE_KEY = "0x019c25498e5e32e022b894f08151416f45551281266787c14d7ab0e9012ed8af";
    VoteData vd = castVoteService.returnVoteData(_contractAddress, PRIVATE_KEY);
    System.out.println(vd.getVoteName());
    System.out.println(vd.getVotingStartTime());
    System.out.println(vd.getVotingEndTime());
    System.out.println(vd.getCandidates());
  }

  @Test
  public void castVote1() throws Exception {
    String _contractAddress = "0x2c28d08d9f0628810fc3c03433b58106f42b9d76";
    String PRIVATE_KEY = "0x019c25498e5e32e022b894f08151416f45551281266787c14d7ab0e9012ed8af";
    String vote = "张祯";
    System.out.println(castVoteService.castVote(_contractAddress, PRIVATE_KEY, vote));
  }

  @Test
  public void castVote2() throws Exception {
    String _contractAddress = "0x2c28d08d9f0628810fc3c03433b58106f42b9d76";
    String PRIVATE_KEY = "0x83e686c5b57dcedf30caa30299b7027086e045d7e252fa730332ed046c032b72";
    String vote = "张三";
    System.out.println(castVoteService.castVote(_contractAddress, PRIVATE_KEY, vote));
  }

  @Test
  public void isVoted() throws Exception {
    // String _contractAddress = "0x2c28d08d9f0628810fc3c03433b58106f42b9d76";
    // String PRIVATE_KEY =
    // "0x83e686c5b57dcedf30caa30299b7027086e045d7e252fa730332ed046c032b72";
    // String vote = "张三";
    // System.out.println(castVoteService.castVote(_contractAddress, PRIVATE_KEY,
    // vote));
  }

  @Test
  public void endVoting() throws Exception {
    String _contractAddress = "0x2c28d08d9f0628810fc3c03433b58106f42b9d76";
    String PRIVATE_KEY = "0x1d6c2a391786e3f9f6f96d55eac91a1b70875f848e982393ed6ad2fd093c4b3e";
    System.out.println(castVoteService.endVoting(_contractAddress, PRIVATE_KEY));
  }

  @Autowired
  TallyResultServiceImpl tallyService;

  @Test
  public void tallyVote() throws Exception {
    String _contractAddress = "0x2c28d08d9f0628810fc3c03433b58106f42b9d76";
    String PRIVATE_KEY = "0x1d6c2a391786e3f9f6f96d55eac91a1b70875f848e982393ed6ad2fd093c4b3e";
    System.out.println(tallyService.tallyResult(_contractAddress, PRIVATE_KEY));
  }
}