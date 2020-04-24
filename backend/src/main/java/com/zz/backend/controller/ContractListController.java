/*
 * @Date: 2020-04-20 21:17:16
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-21 22:51:18
 * 
 * @Description: 
 */
package com.zz.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zz.backend.entity.Contract;
import com.zz.backend.service.impl.ContractServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractListController {
  @Autowired
  ContractServiceImpl contractService;

  @CrossOrigin
  @RequestMapping(value = "api/contractlist", method = RequestMethod.GET)
  @ResponseBody

  public Page<Contract> returnPage(@RequestParam String ownerAddress, long pageIndex) {
    return contractService.returnContractList(ownerAddress, pageIndex);
  }
}