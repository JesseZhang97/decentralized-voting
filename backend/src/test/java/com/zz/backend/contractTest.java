/*
 * @Date: 2020-03-29 01:15:42
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-03-30 01:11:59
 * 
 * @Description:
 */
package com.zz.backend;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.zz.backend.contract.DecVoting;
import com.zz.backend.contract.ExampleContract;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint160;
import org.web3j.crypto.Credentials;

@SpringBootTest
public class contractTest {

  @Test
  public void serviceTest() throws Exception {
    // 创建实例 部署合约
    final String PRIVATE_KEY = "0x24ba380aa0d6aaed30dbb058bdc6e96ab19d9f00d72267a9758080be7a073d91";
    final BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    final BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L); // L being long; capital for clarity
    // get shi li
    Web3j web3j = Web3j.build(new HttpService());
    Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
    // web3ClientVersion
    String web3ClientVersionString = web3ClientVersion.getWeb3ClientVersion();
    System.out.println();
    System.out.println("Web3 client version: " + web3ClientVersionString);
    System.out.println();

    // get credentials
    Credentials credentials = Credentials.create(PRIVATE_KEY);
    System.out.println("Credential KeyPair : " + credentials.getEcKeyPair());
    // deploy contract
    String deployedContract = ExampleContract.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT).send()
        .getContractAddress();
    System.out.println("Deployed contract address; " + deployedContract);
    // FIXME filter
    EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST,
        deployedContract);
    web3j.ethLogFlowable(filter).subscribe(log -> System.out.println(log.toString()));
    // get instance
    ExampleContract exampleContract = ExampleContract.load(deployedContract, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    // operation
    exampleContract.setBalance("Alice", BigInteger.valueOf(4)).send();
    System.out.println("----------------------------------------");
    Object ans = exampleContract.returnBalance("Alice").send();
    System.out.println("user-defined message:   " + ans.toString());
    ans = exampleContract.returnBalance("Bob").send();
    System.out.println("user-defined message:   " + ans.toString());
    System.out.println();
  }

  @Test
  public void setupDataTest() throws Exception
  {
    // 创建实例 部署合约
    final String PRIVATE_KEY = "0x67e25a90fdeb2377d2e3440efc1c150c3d8ae3f7b7dc05e0381195e8d493b2b9";
    final BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    final BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L); // L being long; capital for clarity
    Web3j web3j = Web3j.build(new HttpService());
    Credentials credentials = Credentials.create(PRIVATE_KEY);
    System.out.println("Credential KeyPair : " + credentials.getEcKeyPair());
    // deploy contract
    String deployedContract = DecVoting.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT).send()
        .getContractAddress();
    System.out.println("Deployed contract address; " + deployedContract);
    // get instance
    DecVoting voting = DecVoting.load(deployedContract, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    // operation
    List<Address> _voterAddress = new ArrayList<>();
    _voterAddress.add(new Uint160(0x56d10818a88eec711bbda20f07703c613aa10dc14ff9a3152252059d5e93e54c,0x45abde53202c43b4c9ab576b1d4b35a47fd532f054f17c7172112dcf96287983,0x672700d8c366b8cfa9c6c5b25fc26dffa1fb9f7db7a4a93f7315a711201e7f1f));    
    voting.finishSetUp("_voteName", 2020-03-30, 2020-03-31, 
  2020-03-32, 2020-03-33, 5, 5, _voterAddress, _candidates)
  }
}