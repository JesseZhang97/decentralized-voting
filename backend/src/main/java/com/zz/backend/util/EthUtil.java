/*
 * @Date: 2020-04-02 00:21:55
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-08 17:59:00
 * 
 * @Description:ethereum 操作工具类
 */
package com.zz.backend.util;

import java.net.ConnectException;

import com.zz.backend.contract.DecVoting;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.tx.gas.DefaultGasProvider;

public class EthUtil {
  /**
   * 创建web3实例连接区块链网络
   */
  // static Web3j web3j = Web3j.build(new HttpService());
  // static Web3j web3j = Web3j.build(new
  // HttpService("https://kovan.infura.io/v3/76b5ad0a18394767b129a2a60a7793c6"));
  // static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
  // static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
  // static String PRIVATE_KEY =
  // "0x019c25498e5e32e022b894f08151416f45551281266787c14d7ab0e9012ed8af";
  static WebSocketService ws = new WebSocketService("wss://kovan.infura.io/ws/v3/76b5ad0a18394767b129a2a60a7793c6",
      true);
  static String CREDENTIALS_ERROR = "SOMETHING WENT WRONG IN GETTING CREDENTIALS";

  public static Credentials credentials(String PRIVATE_KEY) throws Exception {
    Credentials credentials = Credentials.create(PRIVATE_KEY);
    return credentials;
  }

  /**
   * 获得credentials并部署合约
   * 
   * @param PRIVATE_KEY
   * @return
   * @throws Exception
   */
  public static String deployContract(String PRIVATE_KEY) throws Exception {
    Web3j web3j = Web3j.build(ws);
    Credentials credentials = Credentials.create(PRIVATE_KEY);// FIXME ??线上获取影响效率,存储为静态资源加载
    if (credentials == null) {
      return CREDENTIALS_ERROR;
    }
    String deployedContract = DecVoting.deploy(web3j, credentials, new DefaultGasProvider()).send()
        .getContractAddress();
    return deployedContract;
  }

  /**
   * 加载合约
   * 
   * @param PRIVATE_KEY
   * @param deployedContract
   * @return
   * @throws ConnectException
   */
  public static DecVoting loadContract(String PRIVATE_KEY, String deployedContract) throws ConnectException {
    Web3j web3j = Web3j.build(ws);
    Credentials credentials = Credentials.create(PRIVATE_KEY);
    DecVoting voting = DecVoting.load(deployedContract, web3j, credentials, new DefaultGasProvider());
    return voting;
  }

  /**
   * @description:
   * @param {type}
   * @return:
   */
  public static void connectEthereum() throws ConnectException {
    ws.connect();
  }

  /**
   * @description:
   * @param {type}
   * @return:
   */
  public static void closeEthereum() {
    ws.close();
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