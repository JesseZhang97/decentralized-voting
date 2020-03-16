/*
 * @Date: 2020-03-14 03:10:38
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-16 21:21:45
 * @FilePath: /decentralized-voting/backend/src/test/java/com/zz/backend/DemoApplicationTests.java
 * @Description: 
 */
package com.zz.backend;

import java.sql.SQLException;

//import java.util.List;

import javax.annotation.Resource;
import javax.security.auth.PrivateCredentialPermission;
import javax.sql.DataSource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zz.backend.mapper.UserMapper;
import com.zz.backend.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoApplicationTests {

	@Resource
	private UserMapper userMapper;
	@Autowired
	private DataSource datasource;
	
	@Test
	public void contextLoads() throws SQLException {
		// System.out.println(datasource.getConnection());
		// QueryWrapper<User> wrapper = new QueryWrapper<User>();
		// wrapper.eq("account", "admin");
		// wrapper.eq("password", "123");
    // // User usercondition = new User();
    // // usercondition.setAccount(user.getAccount());
		// // usercondition.setPassword(user.getPassword());
		// User user = userMapper.selectOne(wrapper);
		// System.out.println(user);
    

		// List<User> userlist = userMapper.selectList(null);
		// 	for (User user : userlist) {
		// 		System.out.println(user);
		// 	}
	}

}
