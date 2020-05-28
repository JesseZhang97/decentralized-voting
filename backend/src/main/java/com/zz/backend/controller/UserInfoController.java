/*
 * @Date: 2020-05-11 23:09:43
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-05-12 01:53:49
 * 
 * @Description:
 */
package com.zz.backend.controller;

import com.zz.backend.entity.User;
import com.zz.backend.service.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
  @Autowired
  UserServiceImpl userService;

  @CrossOrigin
  @RequestMapping(value = "/api/userinfo", method = RequestMethod.GET)
  @ResponseBody
  public User getUserInfo(@RequestParam String address) {
    return userService.UserInfo(address);
  }

  @PostMapping(value = "/api/updateinfo")
  @ResponseBody
  public int updateUserInfo(@RequestBody User requestUser) {
    return userService.updateInfo(requestUser);
  }
}