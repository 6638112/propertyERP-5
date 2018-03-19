package com.cnfantasia.server.common.cryption.symmetry;

import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;

import com.cnfantasia.server.common.cryption.BaseCryptUtil;
import com.cnfantasia.server.common.cryption.CryptConstant;

public class DESCrypt {
  public static final String ALGORITHM = CryptConstant.Crypt_DES;
  
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

  public static void main(String[] args) throws Exception {
    //SecretKey deskey=getKey();
    SecretKey deskey=BaseCryptUtil.getKey(DESCrypt.ALGORITHM, "xx$@%fafaq_salt".getBytes(),56);
    String msg = "123456";
    System.out.println("明文是：" + msg);
    byte[] enc = encrypt(msg.getBytes(), deskey);
    System.out.println("密文是：" + Base64.encodeBase64String(enc)); // a3zSH6WJa5c= 就是123456
    byte[] dec = decrypt(Base64.decodeBase64(Base64.encodeBase64String(enc).getBytes()), deskey);
    System.out.println("解密后的结果是：" + new String(dec));
  }
}