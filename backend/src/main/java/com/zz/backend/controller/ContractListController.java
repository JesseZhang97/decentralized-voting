/*
 * @Date: 2020-04-20 21:17:16
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-20 21:56:54
 * 
 * @Description: 
 */
package com.zz.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zz.backend.entity.Contract;
import com.zz.backend.service.impl.ContractServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractListController {
  @Autowired
  ContractServiceImpl contractService;

  @CrossOrigin
  @PostMapping(value = "api/contractlist")
  @ResponseBody

  public Page<Contract> returnPage(@RequestBody String ownerAddress) {
    return contractService.returnContractList(ownerAddress);
  }
}