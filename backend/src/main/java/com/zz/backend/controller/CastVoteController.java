/*
 * @Date: 2020-04-23 15:45:39
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-24 22:40:45
 * 
 * @Description: 
 */
package com.zz.backend.controller;

import com.zz.backend.entity.VoteData;
import com.zz.backend.service.impl.CastVoteServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CastVoteController {
  @Autowired
  CastVoteServiceImpl castVoteService;

  @CrossOrigin
  @PostMapping(value = "/api/voteinfo")
  @ResponseBody
  public VoteData displayVoteData(@RequestBody VoteData requestVoteData) throws Exception {
    return castVoteService.returnVoteData(requestVoteData);
  }

  @PostMapping(value = "/api/castvote")
  @ResponseBody
  public String castvote(@RequestBody VoteData requestVoteData) throws Exception {
    return castVoteService.castVote(requestVoteData);
  }

  @PostMapping(value = "/api/endvoting")
  @ResponseBody
  public boolean endVotingPhase(@RequestBody VoteData requestVoteData) throws Exception {
    return castVoteService.endVoting(requestVoteData);
  }
}