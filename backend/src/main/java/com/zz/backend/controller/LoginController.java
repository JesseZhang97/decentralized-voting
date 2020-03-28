/*
 * @Date: 2020-03-15 01:53:30
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-03-28 04:04:21
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/controller/LoginController.java
 * @Description: 
 */
package com.zz.backend.controller;

import com.zz.backend.api.Result;
import com.zz.backend.entity.User;
import com.zz.backend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    // @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    // public CommonResult login(@RequestBody User user) {
    // if (user.getAccount().equals("admin") && user.getPassword().equals("123456"))
    // return CommonResult.success("admin");
    // else
    // return CommonResult.validateFailed();
    // }

    @Autowired
    UserServiceImpl userService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        // return userService.get(requestUser);
        User user = userService.get(requestUser);
        if (user == null) {
            return new Result(400);
        } else {
            // session.setAttribute("user", user);
            return new Result(200);
        }

    }

}