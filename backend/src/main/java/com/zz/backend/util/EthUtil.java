/*
 * @Date: 2020-04-02 00:21:55
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-03 23:24:22
 * 
 * @Description:ethereum 操作工具类
 */
package com.zz.backend.util;

import java.math.BigInteger;

import com.zz.backend.contract.DecVoting;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class EthUtil {
  /**
   * 创建web3实例连接区块链网络
   */
  // static Web3j web3j = Web3j.build(new HttpService());
  static Web3j web3j = Web3j.build(new HttpService("https://kovan.infura.io/v3/76b5ad0a18394767b129a2a60a7793c6"));
  static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
  static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

  /**
   * 获得credentials并部署合约
   * 
   * @param PRIVATE_KEY
   * @return
   * @throws Exception
   */
  public static String deployContract(String PRIVATE_KEY) throws Exception {
    Credentials credentials = Credentials.create(PRIVATE_KEY);// FIXME ??线上获取影响效率,存储为静态资源加载
    if (credentials == null) {
      return "SOMETHING WENT WRONG IN GETTING credentials :(";
    }
    String deployedContract = DecVoting.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT).send().getContractAddress();
    System.out.println("Deployed contract address; " + deployedContract);
    return deployedContract;
  }

  /**
   * 加载合约
   * 
   * @param PRIVATE_KEY
   * @param deployedContract
   * @return
   */
  public static DecVoting loadContract(String PRIVATE_KEY, String deployedContract) {
    Credentials credentials = Credentials.create(PRIVATE_KEY);
    DecVoting decVoting = DecVoting.load(deployedContract, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    return decVoting;
  }

  /**
   * @param str
   * @return
   */
  public static byte[] StrToByte(String str) {
    byte[] byteValue = str.getBytes();
    byte[] byteValueLen32 = new byte[32];
    System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
    return byteValueLen32;
  }
}