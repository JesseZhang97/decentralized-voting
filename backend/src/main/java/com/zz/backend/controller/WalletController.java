/*
 * @Date: 2020-03-20 19:25:12
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-03-24 01:25:56
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/controller/WalletController.java
 * @Description: 生成钱包信息绑定用户
 */
package com.zz.backend.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.backend.api.ResultUtil;
import com.zz.backend.entity.Wallet;
import com.zz.backend.service.impl.WalletServiceImpl;

import org.apache.tomcat.util.ExceptionUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;

@RestController
public class WalletController {
  @Autowired
  WalletServiceImpl walletService;

  @CrossOrigin
  @RequestMapping(value = "/api/wallet")
  @ResponseBody
  public Wallet getWallet(@RequestParam(value = "id", required = true) String id) throws CipherException, IOException {
    return walletService.genWallet(id);
    // TODO 用户一次绑定后 无法通过刷新网页再次更改
  }
}
