/*
 * @Date: 2020-04-01 23:02:55
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-10 19:02:37
 * 
 * @Description:用户设定投票内容
 */
package com.zz.backend.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.backend.contract.DecVoting;
import com.zz.backend.entity.Mail;
import com.zz.backend.entity.User;
import com.zz.backend.entity.VoteData;
import com.zz.backend.mapper.UserMapper;
import com.zz.backend.util.EthUtil;
import com.zz.backend.util.UnixTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:处理前端传入的投票信息
 */
@Service
public class SetupVotingServiceImpl extends ServiceImpl<UserMapper, User> {
  public String getVoteData(VoteData vd) throws Exception {
    String ERROR_msg = "ETHEREUM TRANSITION ERROR";
    String TIME_ERROR = "TIME ERROR";
    // get caller ADDRESS and DEPLOY

    EthUtil.connectEthereum();
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
    //   return ERROR_msg;
    // }
    List<String> voterList = new ArrayList<>();
    int numOfVoter = voting.numOfVoters().send().intValue();
    for (int i = 0; i < numOfVoter; i++) {
      BigInteger newI = BigInteger.valueOf(i);
      voterList.add(voting.voterAddress(newI).send().toString());
    }
    getVoterList(voterList, _voteName, contractAddress);
    EthUtil.closeEthereum();
    return contractAddress;
  }

  @Autowired
  private UserMapper userMapper;

  @Autowired
  EmailServiceImpl emailService;

  public boolean getVoterList(List<String> voterList, String _votename, String contractAddress) {
    // 找到voterList地址对应的数据库邮件地址
    User user = new User();
    String mailList = "";
    // TODO QueryWrapper<User> wrapper = new QueryWrapper<User>();
    for (int i = 0; i < voterList.size(); i++) {
      user.setPublickey(voterList.get(i));
      // TODO 空指针问题
      QueryWrapper<User> wrapper = new QueryWrapper<User>();
      wrapper.eq("publickey", user.getPublickey());
      // userMapper.selectOne(wrapper).getEmail();
      mailList += userMapper.selectOne(wrapper).getEmail() + ",";
    }
    mailList = mailList.substring(0, mailList.length() - 1);
    // 发送邮件
    Mail ml = new Mail();
    ml.setFrom("597375428@qq.com");
    ml.setTo(mailList);
    ml.setSubject("注册投票");
    ml.setText("你好,\n你有一个新的可参与投票,投票活动名为：" + _votename + "\n投票地址为：" + "TODO");
    return emailService.emailSender(ml);
    // TODO启动计时器,到时间发送邮件
  }
}