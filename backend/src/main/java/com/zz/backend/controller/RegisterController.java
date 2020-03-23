/*
 * @Date: 2020-03-15 01:53:30
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-21 02:00:49
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/controller/RegisterController.java
 * @Description: 
 */
package com.zz.backend.controller;

import com.zz.backend.api.Result;
import com.zz.backend.entity.User;
import com.zz.backend.service.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    @Autowired
    UserServiceImpl userService;

    @CrossOrigin
    @PostMapping(value = "/api/register")
    @ResponseBody
    public Result register (@RequestBody User requestUser) {
        return userService.set(requestUser);
    }
}