package com.cnfantasia.server.common.cryption;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.cnfantasia.server.common.exception.CryptRuntimeException;

/**
 * 描述:对称加密工具类
 * 
 * @version 1.00
 * @author syl
 * 
 */
public class BaseCryptUtil {
  public static SecretKey getKey(String algorithm){
    // SecretKey负责保存对称密钥
    KeyGenerator keygen=null;
    try {
      // 生成密钥
      keygen = KeyGenerator.getInstance(algorithm);
      // 获取当前使用的Provider
      // System.out.println(keygen.getProvider.getProvider().getClass());
    } catch (NoSuchAlgorithmException e) {
      throw new CryptRuntimeException(e);
    } 
    // Cipher负责完成加密或解密工作
    SecretKey key = keygen.generateKey();
    return key;
  }
  /**
   * 生成密钥
   * 
   * @param seed 种子
   * @return 密钥对象
   */
  public static SecretKey getKey(String algorithm,byte[] seed,int keySize){
  	 // 类用于生成公钥和私钥对，基于RSA算法生成对象
  	KeyGenerator keygen=null;
    try {
      keygen = KeyGenerator.getInstance(algorithm);
    } catch (NoSuchAlgorithmException e) {
      throw new CryptRuntimeException(e);
    }
    // 初始化随机产生器
    if(seed==null||seed.length<=0){
    	keygen.init(keySize); 
    }else{
    	SecureRandom secureRandom = new SecureRandom();
      secureRandom.setSeed(seed);
      keygen.init(keySize,secureRandom);
    }
    // 生成一个密钥对，保存在keyPair中
    SecretKey key = keygen.generateKey();
    return key;
  }
  /**
   * 加密
   * @param algorithm
   * @param data
   * @param key
   * @return
   */
  public static byte[] encrypt(String algorithm,byte[] data,Key key){
    return crypt(algorithm, data, key, Cipher.ENCRYPT_MODE);
  }
  
  /**
   * 解密
   * @param algorithm
   * @param data
   * @param key
   * @return
   */
  public static byte[] decrypt(String algorithm,byte[] data,Key key){
    return crypt(algorithm, data, key, Cipher.DECRYPT_MODE);
  }
  
  /**
   * 私有方法
   * 加密解密操作公共方法
   * @param algorithm
   * @param data
   * @param key
   * @param cryptMode
   * @return
   */
  private static byte[] crypt(String algorithm,byte[] data,Key key,int cryptMode){
    byte[] resData=null;
    // 实例化支持AES算法的密钥生成器，算法名称用AES
    // Security.addProvider(new com.sun.crypto.provider.SunJCE()); 
    try {
      // Cipher负责完成加密或解密工作
      Cipher cipher=Cipher.getInstance(algorithm);
      // 获取当前使用的Provider
//       System.out.println(cipher.getProvider().getClass());
      // 根据密钥，对Cipher对象进行初始化,ENCRYPT_MODE表示加密模式
      cipher.init(cryptMode, key);
      resData=cipher.doFinal(data);
    } catch (Exception e) {
      throw new CryptRuntimeException(e);
    }
    return resData;
  }
}
