package com.cnfantasia.server.common.cryption.oneWay;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.cnfantasia.server.common.cryption.CryptConstant;
import com.cnfantasia.server.common.exception.CryptRuntimeException;

public class SHACrypt {
  public static final String ALGORITHM = CryptConstant.Crypt_SHA;
  /**
   * SHA加密
   * @param data
   * @return
   */
  public static byte[] encrypt(byte[] data) {
    byte[] resData=null;
    try {
      MessageDigest md = MessageDigest.getInstance(ALGORITHM);
//    System.out.println(md.getProvider().getClass());//获取当前使用的Provider
      md.update(data);
      resData=md.digest();
    } catch (NoSuchAlgorithmException e) {
      throw new CryptRuntimeException(e);
    }
    return resData;
  }
  
//  public static void main(String[] args){
//      String msg = "郭克华_安全编程技术";
//      System.out.println("明文是：" + msg);
//      byte[] srcBytes = msg.getBytes();
//      byte[] resultBytes = SHACrypt.encrypt(srcBytes);
//      String result = new String(resultBytes);
//      System.out.println("密文是：" + result);
//  }
  
}