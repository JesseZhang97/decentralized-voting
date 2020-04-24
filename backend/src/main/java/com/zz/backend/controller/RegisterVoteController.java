/*
 * @Date: 2020-04-22 17:03:13
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-23 19:46:29
 * 
 * @Description: 
 */
package com.zz.backend.controller;

import com.zz.backend.entity.VoteData;
import com.zz.backend.service.impl.RegistrationServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterVoteController {
  @Autowired
  RegistrationServiceImpl registrationService;

  @CrossOrigin
  @PostMapping(value = "/api/registervote")
  @ResponseBody
  public String registerVote(@RequestBody VoteData requestRegisterData) throws Exception {

    return registrationService.registrationVoter(requestRegisterData);
  }

  @PostMapping(value = "/api/endregistration")
  @ResponseBody
  public boolean endRegistrationPhase(@RequestBody VoteData requestRegisterData) throws Exception {
    return registrationService.endRegistration(requestRegisterData);
  }
}