/*
 * @Date: 2020-03-29 01:15:42
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-06 04:37:37
 * 
 * @Description:
 */
package com.zz.backend;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.kenai.jffi.Array;
import com.zz.backend.contract.DecVoting;
import com.zz.backend.util.UnixTime;

import org.apache.ibatis.javassist.tools.web.Webserver;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.generated.Uint160;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;

@SpringBootTest
public class contractTest {
  @Test
  public void connectTest() throws Exception {
    final String PRIVATE_KEY = "0x019c25498e5e32e022b894f08151416f45551281266787c14d7ab0e9012ed8af";
    WebSocketService ws = new WebSocketService("wss://kovan.infura.io/ws/v3/76b5ad0a18394767b129a2a60a7793c6", true);
    ws.connect();
    Web3j web3j = Web3j.build(ws);
    Credentials credentials = Credentials.create(PRIVATE_KEY);
    System.out.println("Credential KeyPair : " + credentials.getEcKeyPair());
    Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
    String clientVersion = web3ClientVersion.getWeb3ClientVersion();
    System.out.println(clientVersion);
    ws.close();
  }

  @Test
  public void deployContract() throws Exception {
    final String PRIVATE_KEY = "0x019c25498e5e32e022b894f08151416f45551281266787c14d7ab0e9012ed8af";
    WebSocketService ws = new WebSocketService("wss://kovan.infura.io/ws/v3/76b5ad0a18394767b129a2a60a7793c6", true);
    ws.connect();
    Web3j web3j = Web3j.build(ws);
    Credentials credentials = Credentials.create(PRIVATE_KEY);
    System.out.println("Credential KeyPair : " + credentials.getEcKeyPair());
    String deployedContract = DecVoting.deploy(web3j, credentials, new DefaultGasProvider()).send()
        .getContractAddress();
    System.out.println("Deployed contract address:" + deployedContract);
    ws.close();
  }

  @Test
  public void setupVote() throws Exception {
    final String PRIVATE_KEY = "0x019c25498e5e32e022b894f08151416f45551281266787c14d7ab0e9012ed8af";
    WebSocketService ws = new WebSocketService("wss://kovan.infura.io/ws/v3/76b5ad0a18394767b129a2a60a7793c6", true);
    ws.connect();
    Web3j web3j = Web3j.build(ws);
    Credentials credentials = Credentials.create(PRIVATE_KEY);
    System.out.println("Credential KeyPair : " + credentials.getEcKeyPair());
    String contractAddress = "0xe7f6740c414a0faf12cb4fb605f1fb27d801f9cd";

    DecVoting voting = DecVoting.load(contractAddress, web3j, credentials, new DefaultGasProvider());
    // Filter
    EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST,
        contractAddress);
    // voting.booleanEventEventFlowable(filter).subscribe(log ->
    // System.out.println(log.toString()));
    // voting.booleanEventEventFlowable(filter).subscribe(log ->
    // System.out.println(log.bl));
    // web3j.ethLogFlowable(filter).subscribe(log ->
    // System.out.println(log.toString()));
    // 发起投票
    String _voteName = "张祯";
    BigInteger _registrationStartTime = new BigInteger("1586031900");
    BigInteger _registrationEndTime = new BigInteger("1586031960");
    BigInteger _votingStartTime = new BigInteger("1586032020");
    BigInteger _votingEndTime = new BigInteger("1586032080");
    List<String> _voterAddress = new ArrayList<>();
    _voterAddress.add("0x840e5d7B953DBDE80C10851904170Fe391eB6323");
    _voterAddress.add("0x5Af8713c57818216d4f69Fc60153Fa91E036b4D9");
    _voterAddress.add("0x850757399DEb8D3838C74B62935139a51f3A29d2");
    List<String> _candidates = new ArrayList<>();
    _candidates.add("张三");
    _candidates.add("李四");
    _candidates.add("hape");
    voting.finishSetUp(_voteName, _registrationStartTime, _registrationEndTime, _votingStartTime, _votingEndTime,
        _voterAddress, _candidates).send();
    ws.close();
  }

  @Test
  public void votePhase() throws Exception {
    final String PRIVATE_KEY = "0x019c25498e5e32e022b894f08151416f45551281266787c14d7ab0e9012ed8af";
    WebSocketService ws = new WebSocketService("wss://kovan.infura.io/ws/v3/76b5ad0a18394767b129a2a60a7793c6", true);
    ws.connect();
    Web3j web3j = Web3j.build(ws);
    Credentials credentials = Credentials.create(PRIVATE_KEY);
    System.out.println("Credential KeyPair : " + credentials.getEcKeyPair());
    String contractAddress = "0xe7f6740c414a0faf12cb4fb605f1fb27d801f9cd";

    DecVoting voting = DecVoting.load(contractAddress, web3j, credentials, new DefaultGasProvider());
    // Filter
    EthFilter filter = new EthFilter(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST,
        contractAddress);
    // voting.booleanEventEventFlowable(filter).subscribe(log ->
    // System.out.println(log.toString()));
    // voting.booleanEventEventFlowable(filter).subscribe(log ->
    // System.out.println(log.bl));
    web3j.ethLogFlowable(filter).subscribe(log -> System.out.println(log.toString()));

    BigInteger res = voting.state().send();
    System.out.println(res.toString());
  }

  @Test
  public void setupDataTest() throws Exception {
    // 创建实例 部署合约
    final String PRIVATE_KEY = "0x019c25498e5e32e022b894f08151416f45551281266787c14d7ab0e9012ed8af";
    final BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    final BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L); // L being long; capital for clarity
    Web3j web3j = Web3j.build(new HttpService("https://kovan.infura.io/v3/76b5ad0a18394767b129a2a60a7793c6"));
    Credentials credentials = Credentials.create(PRIVATE_KEY);
    System.out.println("Credential KeyPair : " + credentials.getEcKeyPair());
    // Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
    // String clientVersion = web3ClientVersion.getWeb3ClientVersion();
    // System.out.println(clientVersion);

    // // deploy contract
    // String deployedContract = DecVoting.deploy(web3j, credentials, GAS_PRICE,
    // GAS_LIMIT).send().getContractAddress();
    // System.out.println("Deployed contract address:" + deployedContract);

    // FIXME get instance
    DecVoting voting = DecVoting.load("0xFbd4Ca99A4010870b69604Ffe12Ed8cF0B53F96A", web3j, credentials, GAS_PRICE,
        GAS_LIMIT);
    // 发起投票
    // String _voteName = "张祯厉害";
    // BigInteger _registrationStartTime = new BigInteger("1585972560");
    // BigInteger _registrationEndTime = new BigInteger("1585972620");
    // BigInteger _votingStartTime = new BigInteger("1585972680");
    // BigInteger _votingEndTime = new BigInteger("1585972740");
    // List<String> _voterAddr = new ArrayList<>();
    // _voterAddr.add("0x840e5d7B953DBDE80C10851904170Fe391eB6323");
    // _voterAddr.add("0x5Af8713c57818216d4f69Fc60153Fa91E036b4D9");
    // _voterAddr.add("0x850757399DEb8D3838C74B62935139a51f3A29d2");
    // List<String> _candidates = new ArrayList<>();
    // _candidates.add("张三");
    // _candidates.add("李四");
    // _candidates.add("hape");

    // 开始注册
    // BigInteger res = voting.state().send();
    // System.out.println(res.toString());
    // voting.registerVoter("0x840e5d7B953DBDE80C10851904170Fe391eB6323");
    // 结束注册
    // 给出选票
    // 结束投票
    // 计票

  }

  @Test
  public void unixTime() throws ParseException {
    DateFormat df = new SimpleDateFormat("yyyy-MM-DD HH:mm");
    long epoch = df.parse("2015-09-09 0:0").getTime() / 1000L;
    System.out.println(epoch);
  }
}