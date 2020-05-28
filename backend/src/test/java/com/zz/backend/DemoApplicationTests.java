/*
 * @Date: 2020-03-14 03:10:38
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-05-12 17:05:16
 * @FilePath: /decentralized-voting/backend/src/test/java/com/zz/backend/DemoApplicationTests.java
 * @Description: 
 */
package com.zz.backend;

import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;

//import java.util.List;

import javax.annotation.Resource;
// import javax.sql.DataSource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zz.backend.mapper.UserMapper;
import com.zz.backend.entity.User;
// import com.zz.backend.entity.Wallet;
// import com.zz.backend.service.impl.SetupVotingServiceImpl;
import com.zz.backend.service.impl.WalletServiceImpl;

import org.beykery.eth.WillWallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.CipherException;

@SpringBootTest
public class DemoApplicationTests {

	@Resource
	@Autowired
	private UserMapper userMapper;

	@Test
	public void orgListTest() {
		// QueryWrapper<User> wrapper = new QueryWrapper<>();
		// wrapper.select("DISTINCT organization");
		// List<Map<String, Object>> orgListFromDB = userMapper.selectMaps(wrapper);
		// List<String> orgList = new ArrayList<>();
		// for (Map<String, Object> m : orgListFromDB) { // 取list集合里的那一条Map集合
		// for (String s : m.keySet()) { // 取map集合里的String类型的key，
		// orgList.add(m.get(s).toString());
		// // System.out.println(m.get(s));// 根据key迭代输出value
		// }
		// }
		// System.out.println(orgList);
		// return orgList;

		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("organization", "16022");
		wrapper.select("name", "publickey");
		System.out.println(userMapper.selectMaps(wrapper));
	}

	@Test
	public void contextLoads() throws SQLException, CipherException {

		// User user = new User();
		// user.setId(1);
		// user.setPublickey("0x863197E5F3807bc75742CB2397Aa8158A91203b2");
		// int update = userMapper.updateById(user);

		// 钱包测试代码
		// WillWallet wa = WillWallet.createWithMnemonic(null, "m/44'/60'/0'/0/0");
		// String mnemonic = wa.getMnemonic();
		// String pk = wa.getPrivateKey();
		// String addr = wa.getAddress();

		// System.out.println("助记词：");
		// System.out.println(mnemonic);
		// System.out.println("私钥：");
		// System.out.println(pk);
		// System.out.println("地址：");
		// System.out.println(addr);

		// 注册用户代码
		User user = new User();

		// Wallet wl = walletService.genWallet();

		// System.out.println(wl.getMnemonic());
		// System.out.println(wl.getPrivatekey());
		// System.out.println(wl.getPublickey());

		user.setAccount("aaAaaa");
		user.setPassword("12aaaaa345");
		user.setName("张想");
		user.setOrganization("16022");
		user.setEmail("597375428@qq.com");
		user.setVerified(0);
		int insert = userMapper.insert(user);
		System.out.println(insert);

		// 登录测试代码
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
		// for (User user : userlist) {
		// System.out.println(user);
		// }

	}

	@Autowired
	WalletServiceImpl walletService;

	@Test
	public void test_paper() throws SQLException, CipherException {
		User user = new User();

		// Wallet wl = walletService.genWallet();

		// user.setAccount("aaaaa");
		// user.setPassword("12a5aaaa345");
		// user.setName("张想");
		// user.setPublickey(wl.getPublickey());
		// user.setOrganization("16022");
		// user.setVerified(0);

		int insert = userMapper.insert(user);

		System.out.println(user.getId());
		System.out.println(insert);
	}

	@Test
	public void test_update() throws CipherException {
		User user = new User();

		WillWallet wa = WillWallet.createWithMnemonic(null, "m/44'/60'/0'/0/0");
		// String mnemonic = wa.getMnemonic();
		// String pk = wa.getPrivateKey();
		String addr = wa.getAddress();

		user.setId(23);
		user.setPublickey(addr);
		// int update = userMapper.updateById(user);
		// user.setPublickey(wl.getPublickey());
	}

	@Test
	public void contract() {

	}
}
