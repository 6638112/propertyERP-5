package com.cnfantasia.server.common.cryption.oneWay;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.cnfantasia.server.common.cryption.symmetry.BASE64Crypt;

/**
 * 依赖于HmacMD5Crypt
 * @version 1.00
 * @author syl
 * 
 */
public class HmacMD5Base64Crypt {
//  public static void main(String[] args) {
//    String resStr=getKey();
//    int middle=resStr.length()/2;
//    System.out.println(resStr.substring(0, middle));
//    System.out.println(resStr.substring(middle, resStr.length()));
//  }
  /**
   * 初始化HMAC密钥
   * @return
   */
  public static String getKey(){
    SecretKey secretKey = HmacMD5Crypt.getKey();
    String resKey = BASE64Crypt.encrypt(secretKey.getEncoded());
    return resKey;
  }

  /**
   * HMAC加密
   * @param data
   * @param key
   * @return
   */
  public static byte[] encrypt(byte[] data, String key){
    SecretKey secretKey = new SecretKeySpec(BASE64Crypt.decrypt(key), HmacMD5Crypt.ALGORITHM);
    return HmacMD5Crypt.encrypt(data, secretKey);
  }
  
}
