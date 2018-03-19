package com.cnfantasia.server.common.cryption.dissymmetry;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.cnfantasia.server.common.cryption.BaseCryptUtil;
import com.cnfantasia.server.common.cryption.CryptConstant;
import com.cnfantasia.server.common.exception.CryptRuntimeException;

public class RSACrypt {
  public static final String ALGORITHM = CryptConstant.Crypt_RSA;
  
  /**
   * 获取密钥对
   * @param keySize
   * @return
   */
  public static KeyPair getKeyPair(int keySize){
  	return getKeyPair(null, keySize);
  }
  /**
   * 生成密钥
   * 
   * @param seed 种子
   * @return 密钥对象
   */
  public static KeyPair getKeyPair(byte[] seed,int keySize){
  	 // 类用于生成公钥和私钥对，基于RSA算法生成对象
    KeyPairGenerator keyPairGen=null;
    try {
      keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
    } catch (NoSuchAlgorithmException e) {
      throw new CryptRuntimeException(e);
    }
    // 初始化随机产生器
    if(seed==null||seed.length<=0){
    	keyPairGen.initialize(keySize); 
    }else{
    	SecureRandom secureRandom = new SecureRandom();
      secureRandom.setSeed(seed);
      keyPairGen.initialize(keySize, secureRandom);
    }
    // 生成一个密钥对，保存在keyPair中
    KeyPair keyPair = keyPairGen.generateKeyPair();
    return keyPair;
  }
  /**
   * 加密
   * @param data
   * @param pKey
   * @return
   */
  public static byte[] encrypt(byte[] data,Key pKey) {
    return BaseCryptUtil.encrypt(ALGORITHM, data, pKey);
  }
  /**
   * 解密
   * @param data
   * @param pKey
   * @return
   */
  public static byte[] decrypt(byte[] data,Key pKey) {
    return BaseCryptUtil.decrypt(ALGORITHM, data, pKey);
  }
  /**
   * 取得私钥
   * @param keyPair
   * @return
   */
  public static Key getPrivateKey(KeyPair keyPair) {
    return keyPair.getPrivate();
  }

  /**
   * 取得公钥
   * @param keyPair
   * @return
   */
  public static Key getPublicKey(KeyPair keyPair) {
    return keyPair.getPublic();
  }
  
  public static void main(String[] args) {
    int keySize=1024;
    String msg = "郭克华_安全编程技术";
    System.out.println("明文是:" + msg); // KeyPairGenerator
    KeyPair keyPair = getKeyPair(keySize);
    // 得到私钥
    RSAPrivateKey privateKey = (RSAPrivateKey) getPrivateKey(keyPair); 
    // 得到公钥
    RSAPublicKey publicKey = (RSAPublicKey) getPublicKey(keyPair); 
    
    // 用公钥加密
    byte[] srcBytes = msg.getBytes();
    byte[] resultBytes = encrypt(srcBytes,publicKey);
    System.out.println("用公钥加密后密文是:" + new String(resultBytes)); 
    // 用私钥解密
    byte[] decBytes = decrypt(resultBytes,privateKey);
    String dec = new String(decBytes);
    System.out.println("用私钥解密后结果是:" + dec);
  }
 
}