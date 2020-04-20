/*
 * @Date: 2020-03-29 01:15:42
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-04-20 20:27:38
 * 
 * @Description:
 */
package com.zz.backend;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zz.backend.contract.DecVoting;
import com.zz.backend.entity.Contract;
import com.zz.backend.entity.Mail;
import com.zz.backend.entity.VoteData;
import com.zz.backend.service.impl.ContractServiceImpl;
import com.zz.backend.service.impl.EmailServiceImpl;
import com.zz.backend.service.impl.SetupVotingServiceImpl;
import com.zz.backend.util.EthUtil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.tx.gas.DefaultGasProvider;

import org.web3j.crypto.Credentials;

@SpringBootTest
public class contractTest {

  @Autowired
  ContractServiceImpl contractService;

  @Test
  public void pageTest() {
    String ownerAddress = "0x863197E5F3807bc75742CB2397Aa8158A91203b2";
    Page<Contract> page = contractService.returnContractList(ownerAddress);
    System.out.println(page.getRecords());
    System.out.println(page.getTotal());
    System.out.println(page.getCurrent());
    System.out.println(page.getSize());
    System.out.println(page.hasNext());
  }

  @Test
  public void unixTimeTest() throws Exception {
    long time = System.currentTimeMillis();
    String nowTimeStamp = String.valueOf(time / 1000);
    System.out.println(nowTimeStamp);
  }

  @Test
  public void credentialsTest() throws Exception {
    String PRIVATE_KEY = "0x019c25498e5e32e022b894f08151416f45551281266787c14d7ab0e9012ed8af";
    EthUtil.connectEthereum();
    Credentials credentials = EthUtil.credentials(PRIVATE_KEY);
    String voterAddress = credentials.getAddress();
    System.out.println(voterAddress);
  }

  @Test
  public void stringTest() {
    String _votename = "Hello World";
    // System.out.print("\u001B[1m" + value);
    System.out.print("你好,\n你有一个新的可参与投票,投票活动名为：" + "\u001B[1m" + _votename + "\n投票地址为：" + "TODO");
  }

  @Autowired
  SetupVotingServiceImpl setUpService;

  @Test
  public void emailTestDB() throws Exception {
    List<String> _voterAddress = new ArrayList<>();
    _voterAddress.add("0x840e5d7B953DBDE80C10851904170Fe391eB6323");
    _voterAddress.add("0x5Af8713c57818216d4f69Fc60153Fa91E036b4D9");
    _voterAddress.add("0x850757399DEb8D3838C74B62935139a51f3A29d2");
    String _votename = "显而易见测试成功了呢2";
    String txhash = "";
    // System.out.println(setUpService.getVoterList(_voterAddress, _votename));
    // System.out.println(setUpService.getVoterList(_voterAddress, _votename));
    // String output =
    // System.out.println(setUpService.getVoterList(_voterAddress, _votename,
    // txhash));
  }

  @Autowired
  EmailServiceImpl emailService;

  @Test
  public void emailTest() throws Exception {
    Mail ml = new Mail();
    ml.setFrom("597375428@qq.com");
    ml.setTo("zz597375428@gmail.com");
    ml.setSubject("投票主题张祯大帅比");
    ml.setText("你好,\n你有一个新的可参与投票,投票活动名为：" + "<strong> _votename </strong>" + "\n投票地址为：" + "TODO");

    emailService.emailSender(ml);

  }

  @Test
  public void reg() throws Exception {
    final String PRIVATE_KEY = "0x019c25498e5e32e022b894f08151416f45551281266787c14d7ab0e9012ed8af";
    WebSocketService ws = new WebSocketService("wss://kovan.infura.io/ws/v3/76b5ad0a18394767b129a2a60a7793c6", true);
    ws.connect();
    Web3j web3j = Web3j.build(ws);
    Credentials credentials = Credentials.create(PRIVATE_KEY);
    String contractAddress = "0x06CD631c32a8C0F703A5F1cA1CF5e2e5791EDfBb";
    DecVoting voting = DecVoting.load(contractAddress, web3j, credentials, new DefaultGasProvider());
    // 获取投票人
    // int numOfVoter = voting.numOfVoters().send().intValue();
    // for (int i = 0; i < numOfVoter; i++) {
    // BigInteger newI = BigInteger.valueOf(i);
    // System.out.println(voting.voterAddress(newI).send().toString());
    // }
    // 注册用户
    // voting.registerVoter("0x840e5d7B953DBDE80C10851904170Fe391eB6323").send();
    // System.out.println(voting.returnbool().send().booleanValue());
    // System.out.println(voting.registeredVoters("0x840e5d7B953DBDE80C10851904170Fe391eB6323").send());
    // System.out.println(voting.registeredVoters("0x5Af8713c57818216d4f69Fc60153Fa91E036b4D9").send());
    // 获取竞选人
    int numOfCandidates = voting.numOfCandidates().send().intValue();
    // for (int i = 0; i < numOfCandidates; i++) {
    // BigInteger newI = BigInteger.valueOf(i);
    // System.out.println(voting.candidates(newI).send().toString());
    // }
  }

  // @Autowired
  // SetupVotingServiceImpl setUpService;

  @Test
  public void setupService() throws Exception {
    String _voteName = "happy ending";
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
    _candidates.add("1234");
    String _callerPK = "0x019c25498e5e32e022b894f08151416f45551281266787c14d7ab0e9012ed8af";

    VoteData vd = new VoteData();
    vd.setCallerPRIVATEKEY(_callerPK);
    vd.setVoteName(_voteName);
    // FIXME bigint and string problem
    // vd.setRegistrationStartTime(_registrationStartTime);
    // vd.setRegistrationEndTime(_registrationEndTime);
    // vd.setVotingStartTime(_votingStartTime);
    // vd.setVotingEndTime(_votingEndTime);
    // vd.setVoterAddr(_voterAddress);

    // vd.setCandidates(_candidates);

    String hash = setUpService.getVoteData(vd);
    System.out.println(hash);
    // String hx =
    // EthUtil.deployContract("0x019c25498e5e32e022b894f08151416f45551281266787c14d7ab0e9012ed8af");
    // System.out.println(hx);
  }

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
    String contractAddress = "0x5959cdefb65d4e56a0b1771b1fdf72ac4f36daab";

    DecVoting voting = DecVoting.load(contractAddress, web3j, credentials, new DefaultGasProvider());
    // Filter
    // EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,
    // DefaultBlockParameterName.LATEST,
    // contractAddress);
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
    String t1 = voting.finishSetUp(_voteName, _registrationStartTime, _registrationEndTime, _votingStartTime,
        _votingEndTime, _voterAddress, _candidates).send().toString();
    System.out.println(t1);
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
    // web3j.ethLogFlowable(filter).subscribe(log ->
    // System.out.println(log.toString()));

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

    // get instance
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