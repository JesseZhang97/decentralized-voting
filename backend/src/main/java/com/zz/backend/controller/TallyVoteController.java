/*
 * @Date: 2020-04-25 00:48:43
 * @Author: zhen
 * @LastEditTime: 2020-04-25 00:53:32
 * @Description: 
 */
package com.zz.backend.controller;

import java.util.Map;

import com.zz.backend.entity.VoteData;
import com.zz.backend.service.impl.TallyResultServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TallyVoteController {
  @Autowired
  TallyResultServiceImpl tallyVoteService;

  @CrossOrigin
  @PostMapping(value = "/api/tallyvoting")
  @ResponseBody
  public Map<String, Integer> tallyVote(@RequestBody VoteData requestVoteData) throws Exception {
    return tallyVoteService.tallyResult(requestVoteData);
  }
}