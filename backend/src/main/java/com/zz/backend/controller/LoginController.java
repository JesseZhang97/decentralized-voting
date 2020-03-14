/*
 * @Date: 2020-03-15 01:53:30
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-15 03:44:00
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/controller/LoginController.java
 * @Description: 
 */
package com.zz.backend.controller;

import com.zz.backend.api.CommonResult;
import com.zz.backend.api.IErrorCode;
import com.zz.backend.entity.User;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody User user) {
        if (user.getAccount().equals("admin") && user.getPassword().equals("123456"))
            return CommonResult.success("admin");
        else
            return CommonResult.validateFailed();
    }
}