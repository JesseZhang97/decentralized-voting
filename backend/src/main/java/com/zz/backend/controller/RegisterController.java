/*
 * @Date: 2020-03-15 01:53:30
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-17 02:04:29
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/controller/RegisterController.java
 * @Description: 
 */
package com.zz.backend.controller;

import com.zz.backend.api.CommonResult;
import com.zz.backend.api.Result;
//import com.zz.backend.api.IErrorCode;
import com.zz.backend.entity.User;
import com.zz.backend.service.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
//import java.util.HashMap;
//import java.util.Map;

@RestController
public class RegisterController {

    // @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    // public CommonResult login(@RequestBody User user) {
    //     if (user.getAccount().equals("admin") && user.getPassword().equals("123456"))
    //         return CommonResult.success("admin");
    //     else
    //         return CommonResult.validateFailed();
    // }

    @Autowired
    UserServiceImpl userService;

    @CrossOrigin
    @PostMapping(value = "/api/register")
    @ResponseBody
    public Result register (@RequestBody User requestUser, HttpSession session) {

        // User user = userService.get(requestUser);
        // if(null == user) {
        //     return new Result(400);
        // } else {
        //     session.setAttribute("user", user);
        //     return new Result(200);
        // }
    }
    
}