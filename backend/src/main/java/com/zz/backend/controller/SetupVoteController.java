/*
 * @Date: 2020-04-04 05:02:55
 * @Author: zhen
 * @LastEditTime: 2020-04-08 00:34:46
 * @Description: 
 */
package com.zz.backend.controller;

import com.zz.backend.entity.VoteData;
import com.zz.backend.service.impl.SetupVotingServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SetupVoteController {
  @Autowired
  SetupVotingServiceImpl setUpService;

  @CrossOrigin
  @PostMapping(value = "/api/setup")
  @ResponseBody
  public String setupVote(@RequestBody VoteData requestVoteData) throws Exception {
    if (requestVoteData == null) {
      return "-1";
    }
    return setUpService.getVoteData(requestVoteData);
  }
}
