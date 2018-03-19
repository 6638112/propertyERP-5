package com.cnfantasia.server.common.cryption.oneWay;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.cnfantasia.server.common.cryption.CryptConstant;
import com.cnfantasia.server.common.exception.CryptRuntimeException;

public class MD5Crypt {
  public static final String ALGORITHM = CryptConstant.Crypt_MD5;
  
  /**
   *  MD5加密
   * @param msg 明文
   * @return 加密结果
   */
  public static byte[] encrypt(byte[] data) {
    byte[] resData=null;
    try {
      // 根据MD5算法生成MessageDigest对象
      MessageDigest md = MessageDigest.getInstance(ALGORITHM);
      // 获取当前使用的Provider
      // System.out.println(md5.getProvider().getClass());
      // 使用data更新摘要,完成哈希计算,得到result
      md.update(data); 
      resData = md.digest();
    } catch (NoSuchAlgorithmException e) {
      throw new CryptRuntimeException(e);
    }
    return resData;
  }

//  public static void main(String[] args) {
//    String msg = "郭克华_安全编程技术";
//    System.out.println("明文是：" + msg);
//    byte[] resultBytes = encrypt(msg.getBytes());
//    String result = new String(resultBytes);
//    System.out.println("密文是：" + result);
//  }
}
