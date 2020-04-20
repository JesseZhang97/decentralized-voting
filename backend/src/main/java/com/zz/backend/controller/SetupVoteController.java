/*
 * @Date: 2020-04-04 05:02:55
 * @Author: zhen
 * @LastEditTime: 2020-04-18 23:17:19
 * @Description: 
 */
package com.zz.backend.controller;

import java.util.List;
import java.util.Map;

import com.zz.backend.entity.VoteData;
import com.zz.backend.service.impl.ReturnDataService;
import com.zz.backend.service.impl.SetupVotingServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class SetupVoteController {
  @Autowired
  SetupVotingServiceImpl setUpService;

  @CrossOrigin
  @PostMapping(value = "/api/setup")
  @ResponseBody
  public String setupVote(@RequestBody VoteData requestVoteData) throws Exception {

    return setUpService.getVoteData(requestVoteData);
  }

  @Autowired
  ReturnDataService returnData;

  @RequestMapping(value = "/api/orglist", method = RequestMethod.GET)
  @ResponseBody
  public List<String> voterList() {
    return returnData.returnOrgList();
  }

  @RequestMapping(value = "/api/voterlist", method = RequestMethod.GET)
  public List<Map<String, Object>> requestMethodName(@RequestParam String orgname) {
    return returnData.returnVoterList(orgname);
  }

}
