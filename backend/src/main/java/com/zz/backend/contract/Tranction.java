/*
 * @Date: 2020-04-01 18:39:42
 * @Author: zhen
 * @LastEditTime: 2020-04-01 18:39:43
 * @Description: 
 */
package com.zz.backend.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tranction {

    // 发送方地址
    private String from;

    // 接收方地址
    private String to;

    // 交易数量
    private String value;

    // 代币合约地址
    private String coinAddress;

    // 地址交易编号
    private BigInteger nonce;

    // 支付的矿工费
    private BigInteger gasPrice;

    // 最大交易矿工费
    private BigInteger gasLimit;

}
