/*
 * @Date: 2020-03-20 02:00:39
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-23 23:02:18
 * @FilePath: /decentralized-voting/backend/src/main/java/com/zz/backend/service/impl/WalletServiceImpl.java
 * @Description: eth钱包功能实现
 */

package com.zz.backend.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.zz.backend.entity.User;
import com.zz.backend.entity.Wallet;
import com.zz.backend.mapper.UserMapper;

import org.apache.tomcat.util.codec.binary.Base64;
import org.beykery.eth.WillWallet;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.web3j.crypto.CipherException;

@Service
/**
 * @description: 生成公钥
 */
public class WalletServiceImpl extends ServiceImpl<UserMapper, User> {
  @Autowired
  private UserMapper userMapper;

  public Wallet genWallet (String userID) throws CipherException, IOException {
    //生成钱包信息
    WillWallet wa = WillWallet.createWithMnemonic(null, "m/44'/60'/0'/0/0");
    //存入bean object
    Wallet wl = new Wallet();
    User user = new User();

    wl.setMnemonic(wa.getMnemonic());
    wl.setPrivatekey(wa.getPrivateKey());
    wl.setPublickey(wa.getAddress());
    wl.setQrcode(crateQRCode(wl.getPublickey(), 250, 250));
    System.out.println(Integer.valueOf(userID).intValue());
    user.setId(Integer.valueOf(userID).intValue());
    user.setPublickey(wl.getPublickey());
    // System.out.println(user);
    // System.out.println(wl);
    int update = userMapper.updateById(user);
    

    return wl;
  }

  public String crateQRCode(String content, int width, int height) throws IOException {

    String resultImage = "";
    if (!StringUtils.isEmpty(content)) {
      ServletOutputStream stream = null;
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      @SuppressWarnings("rawtypes")
      HashMap<EncodeHintType, Comparable> hints = new HashMap<>();
      hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 指定字符编码为“utf-8”
      hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); // 指定二维码的纠错等级为中级
      hints.put(EncodeHintType.MARGIN, 2); // 设置图片的边距

      try {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ImageIO.write(bufferedImage, "png", os);
        /**
         * 原生转码前面没有 data:image/png;base64 这些字段，返回给前端是无法被解析，可以让前端加，也可以在下面加上
         */
        resultImage = new String("data:image/png;base64," + Base64.encodeBase64String(os.toByteArray()));
      return resultImage;
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (stream != null) {
          stream.flush();
          stream.close();
        }
      }
    }
    return null;
}
}