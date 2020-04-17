/*
 * @Date: 2020-03-20 19:25:12
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-04-17 23:49:06
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/controller/WalletController.java
 * @Description: 生成钱包信息绑定用户
 */
package com.zz.backend.controller;

import java.io.IOException;

import com.zz.backend.entity.Wallet;
import com.zz.backend.service.impl.WalletServiceImpl;

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
  }
}
