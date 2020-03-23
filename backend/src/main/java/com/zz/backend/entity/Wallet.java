/*
 * @Date: 2020-03-20 19:11:16
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-23 22:57:31
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/entity/Wallet.java
 * @Description: 
 */
package com.zz.backend.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Wallet {

  private String privatekey;
  private String publickey;
  private String mnemonic;
  private String qrcode;
}