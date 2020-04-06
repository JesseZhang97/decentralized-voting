/*
 * @Date: 2020-04-04 05:02:55
 * @Author: zhen
 * @LastEditTime: 2020-04-06 04:26:00
 * @Description: 
 */
package com.zz.backend.controller;

import com.zz.backend.entity.voteData;
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
  public String setupVote(@RequestBody voteData requestVoteData) throws Exception {
    if (requestVoteData == null) {
      return null;
    }
    return setUpService.getVoteData(requestVoteData);
  }
}
