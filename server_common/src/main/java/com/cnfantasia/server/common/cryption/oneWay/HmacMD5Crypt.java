package com.cnfantasia.server.common.cryption.oneWay;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

import com.cnfantasia.server.common.cryption.CryptConstant;
import com.cnfantasia.server.common.exception.CryptRuntimeException;

/**
 * HMAC是密钥相关的哈希运算消息认证码（Hash-based Message Authentication Code）,
 * HMAC运算利用哈希算法，以一个密钥和一个消息为输入，生成一个消息摘要作为输出
 * 
 * @version 1.00
 * @author syl
 * 
 */
public class HmacMD5Crypt {
  public static final String ALGORITHM = CryptConstant.Crypt_HmacMD5;
  
  /**
   * 初始化HMAC密钥
   * @return
   */
  public static SecretKey getKey(){
    SecretKey secretKey=null;
    KeyGenerator keyGenerator=null;
    try {
      keyGenerator = KeyGenerator.getInstance(ALGORITHM);
      secretKey = keyGenerator.generateKey();
    } catch (Exception e) {
      throw new CryptRuntimeException(e);
    }
    return secretKey;
  }

  /**
   * HMAC加密
   * 
   * @param data
   * @param secretKey
   * @return
   */
  public static byte[] encrypt(byte[] data, SecretKey secretKey){
    Mac mac=null;
    try {
      mac = Mac.getInstance(ALGORITHM);
      mac.init(secretKey);
    } catch (Exception e) {
      throw new CryptRuntimeException(e);
    }
    return mac.doFinal(data);
  }
  
}
