/*
 * @Date: 2020-04-01 23:02:55
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-05-15 16:52:57
 * 
 * @Description:用户设定投票内容
 */
package com.zz.backend.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.backend.contract.DecVoting;
import com.zz.backend.entity.Contract;
import com.zz.backend.entity.Mail;
import com.zz.backend.entity.User;
import com.zz.backend.entity.VoteData;
import com.zz.backend.mapper.ContractMapper;
import com.zz.backend.mapper.UserMapper;
import com.zz.backend.util.EthUtil;
import com.zz.backend.util.UnixTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:处理前端传入的投票信息 提交到kovan
 */
@Service
public class SetupVotingServiceImpl extends ServiceImpl<UserMapper, User> {
  /**
   * 部署合约并创建投票
   * 
   * @param vd
   * @return
   * @throws Exception
   */
  public String getVoteData(VoteData vd) throws Exception {
    String TIME_ERROR = "TIME ERROR";
    // get caller ADDRESS and DEPLOY
    EthUtil.connectEthereum();
    String contractAddress = EthUtil.deployContract(vd.getCallerPRIVATEKEY());
    // load contract
    DecVoting voting = EthUtil.loadContract(vd.getCallerPRIVATEKEY(), contractAddress);
    String _voteName = vd.getVoteName();
    BigInteger _registrationStartTime = new BigInteger(vd.getRegistrationStartTime());
    BigInteger _registrationEndTime = new BigInteger(vd.getRegistrationEndTime());
    BigInteger _votingStartTime = new BigInteger(vd.getVotingStartTime());
    BigInteger _votingEndTime = new BigInteger(vd.getVotingEndTime());
    List<String> _voterAddr = new ArrayList<String>(Arrays.asList(vd.getVoterAddr()));
    List<String> _candidates = new ArrayList<String>(Arrays.asList(vd.getCandidates()));
    // List<String> _voterAddr = vd.getVoterAddr();
    // List<String> _candidates = vd.getCandidates();

    if (_registrationStartTime.compareTo(UnixTime.getNowTimeStamp()) < 0) {
      return TIME_ERROR;
    }
    if (_registrationStartTime.compareTo(_registrationEndTime) > 0) {
      return TIME_ERROR;
    }
    if (_registrationEndTime.compareTo(_votingStartTime) > 0) {
      return TIME_ERROR;
    }
    if (_votingStartTime.compareTo(_votingEndTime) > 0) {
      return TIME_ERROR;
    }

    voting.finishSetUp(_voteName, _registrationStartTime, _registrationEndTime, _votingStartTime, _votingEndTime,
        _voterAddr, _candidates).send();
    // if (voting.returnbool().send().booleanValue() == false) {
    // return ERROR_msg;
    // }
    List<String> voterList = new ArrayList<>();
    int numOfVoter = voting.numOfVoters().send().intValue();
    for (int i = 0; i < numOfVoter; i++) {
      BigInteger newI = BigInteger.valueOf(i);
      voterList.add(voting.voterAddress(newI).send().toString());
    }
    if (contractAddress != null) {
      setVoterList(voterList, contractAddress, vd);
      setContract2DB(vd, contractAddress);
    }
    return contractAddress;
  }

  @Autowired
  private UserMapper userMapper;

  @Autowired
  EmailServiceImpl emailService;

  /**
   * 发送邮件给投票人
   * 
   * @param voterList
   * @param contractAddress
   * @param vd
   * @return
   */
  public boolean setVoterList(List<String> voterList, String contractAddress, VoteData vd) {
    // 找到voterList地址对应的数据库邮件地址
    User user = new User();
    String mailList = "";
    for (int i = 0; i < voterList.size(); i++) {
      user.setPublickey(voterList.get(i));
      QueryWrapper<User> wrapper = new QueryWrapper<User>();
      wrapper.eq("publickey", user.getPublickey());
      // userMapper.selectOne(wrapper).getEmail();
      mailList += userMapper.selectOne(wrapper).getEmail() + ",";
    }
    mailList = mailList.substring(0, mailList.length() - 1);
    // 处理时间戳
    String rgStart = UnixTime.timeStampToDate(vd.getRegistrationStartTime());
    String rgEnd = UnixTime.timeStampToDate(vd.getRegistrationEndTime());
    String vtStart = UnixTime.timeStampToDate(vd.getVotingStartTime());
    String vtEnd = UnixTime.timeStampToDate(vd.getVotingEndTime());
    // 发送邮件
    Mail ml = new Mail();
    ml.setFrom("597375428@qq.com");
    ml.setTo(mailList);
    System.out.println(mailList);
    ml.setSubject("您有新的可参与投票活动");
    ml.setText("您好,\n你有一个新的可参与投票,投票活动名为：" + vd.getVoteName() + "\n可注册时间为:" + rgStart + "--" + rgEnd + "\n可投票时间为:"
        + vtStart + "--" + vtEnd + "\n投票地址为：" + contractAddress);
    return emailService.emailSender(ml);
  }

  @Autowired
  ContractMapper contractMapper;

  /**
   * 存储合约到db
   * 
   * @param vd
   * @param contractAddress
   * @return
   */
  public boolean setContract2DB(VoteData vd, String contractAddress) {
    Contract contract = new Contract();
    contract.setAddress(contractAddress);
    contract.setOwner(vd.getCallerPUBLICKEY());
    contract.setRegistrationStartTime(new BigInteger(vd.getRegistrationStartTime()));
    contract.setRegistrationEndTime(new BigInteger(vd.getRegistrationEndTime()));
    contract.setVotingStartTime(new BigInteger(vd.getVotingStartTime()));
    contract.setVotingEndTime(new BigInteger(vd.getVotingEndTime()));

    if (vd != null) {
      int insert = contractMapper.insert(contract);
      if (insert != 0) {
        return true;
      }
    }
    return false;
  }
}