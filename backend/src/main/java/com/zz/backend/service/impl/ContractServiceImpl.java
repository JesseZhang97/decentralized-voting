/*
 * @Date: 2020-04-20 19:02:50
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-20 19:39:49
 * 
 * @Description: 
 */
package com.zz.backend.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.backend.entity.Contract;
import com.zz.backend.mapper.ContractMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> {
  @Autowired
  private ContractMapper contractMapper;

  public Page<Contract> returnContractList(String ownerAddress) {
    Page<Contract> page = new Page<Contract>(1, 1);

    QueryWrapper<Contract> wrapper = new QueryWrapper<>();
    wrapper.eq("owner", ownerAddress);

    Page<Contract> newPage = contractMapper.selectPage(page, wrapper);
    return newPage;
  }
}