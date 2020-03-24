/*
 * @Date: 2020-03-16 18:17:20
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-03-24 01:17:24
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/service/impl/UserServiceImpl.java
 * @Description: 用户的登录与注册
 */
package com.zz.backend.service.impl;

import com.zz.backend.api.Result;
import com.zz.backend.entity.User;
import com.zz.backend.mapper.UserMapper;
// import com.zz.backend.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhen
 * @since 2020-03-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> {
  @Autowired
  private UserMapper userMapper;

  // 登录
  public User get(User user) {
    QueryWrapper<User> wrapper = new QueryWrapper<User>();
    wrapper.eq("account", user.getAccount());
    wrapper.eq("password", user.getPassword());

    return userMapper.selectOne(wrapper);
  }

  /**
   * @description: 注册
   * @param {User}
   * @return: int
   */
  public Result set(User user) {
    user.setAccount(user.getAccount());
    user.setPassword(user.getPassword());
    user.setName(user.getName());
    user.setOrganization(user.getOrganization());
    user.setVerified(0);
    int insert = userMapper.insert(user);

    Result result = new Result();
    if (insert != 0) {
      result.setCode(200);
      result.setData(String.valueOf(user.getId()));
    } else {
      result.setCode(400);
    }
    return result;
  }
}
