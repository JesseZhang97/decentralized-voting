/*
 * @Date: 2020-03-29 01:15:42
 * 
 * @Author: zhen
 * 
 * @LastEditTime: 2020-03-29 03:39:03
 * 
 * @Description:
 */
package com.zz.backend;

import java.io.IOException;
import java.math.BigInteger;

import com.zz.backend.contract.ExampleContract;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.crypto.Credentials;

@SpringBootTest
public class contractTest {

  @Test
  public void serviceTest() throws Exception {
    // 创建实例 部署合约
    final String PRIVATE_KEY = "0xc632bef89ea3f785454fab0745699a1c7cb4209eebb1cba79c7a0ec38ec94a5e";
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
}