/*
 * @Date: 2020-03-14 03:10:38
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-15 01:57:21
 * @FilePath: /backend/src/test/java/com/zz/backend/DemoApplicationTests.java
 * @Description: 
 */
package com.zz.backend;

import java.util.List;

import javax.annotation.Resource;

// import com.zz.backend.mapper.UserMapper;
import com.zz.backend.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoApplicationTests {

	// @Resource
	// private UserMapper userMapper;

	@Test
	public void contextLoads() {
		// List<User> userlist = userMapper.selectList(null);
		// 	for (User user : userlist) {
		// 		System.out.println(user);
		// 	}
	}

}
