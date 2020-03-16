/*
 * @Date: 2020-03-16 18:17:20
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-17 02:28:49
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/service/impl/UserServiceImpl.java
 * @Description: 
 */
package com.zz.backend.service.impl;

import com.zz.backend.entity.User;
import com.zz.backend.mapper.UserMapper;
import com.zz.backend.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jsqlparser.statement.select.Select;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhen
 * @since 2020-03-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
  @Autowired
  private UserMapper userMapper;
  //登录
  public User get(User user) {
    QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.eq("account", user.getAccount());
		wrapper.eq("password", user.getPassword());
    // User usercondition = new User();
    // usercondition.setAccount(user.getAccount());
		// usercondition.setPassword(user.getPassword());
		return userMapper.selectOne(wrapper);
  }
  // public User set(User user) {
    
  // }
}
