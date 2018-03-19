package com.cnfantasia.server.common.cryption.symmetry;

import javax.crypto.SecretKey;

import com.cnfantasia.server.common.cryption.BaseCryptUtil;
import com.cnfantasia.server.common.cryption.CryptConstant;

public class DESedeCrypt {
  public static final String ALGORITHM = CryptConstant.Crypt_DESede;
  /**
   * 获取密钥
   * @return
   */
  public static SecretKey getKey(){
    return BaseCryptUtil.getKey(ALGORITHM);
  }
  /**
   * 加密
   * @param data
   * @param deskey
   * @return
   */
  public static byte[] encrypt(byte[] data,SecretKey deskey){
    return BaseCryptUtil.encrypt(ALGORITHM, data, deskey);
  }
  /**
   * 解密
   * @param data
   * @param deskey
   * @return
   */
  public static byte[] decrypt(byte[] data,SecretKey deskey){
    return BaseCryptUtil.decrypt(ALGORITHM, data, deskey);
  }
  
//  public static void main(String[] args) throws Exception {
//    SecretKey deskey=getKey(); 
//    String msg = "郭克华_安全编程技术";
//    System.out.println("明文是：" + msg);
//    byte[] src = msg.getBytes(); // 加密，结果保存进enc
//    byte[] enc = encrypt(src, deskey);
//    System.out.println("密文是：" + new String(enc));
//    
//    byte[] dec = decrypt(enc, deskey);
//    System.out.println("解密后的结果是：" + new String(dec));
//  }
}