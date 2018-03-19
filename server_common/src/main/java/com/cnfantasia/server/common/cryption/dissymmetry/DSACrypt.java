package com.cnfantasia.server.common.cryption.dissymmetry;

import static org.junit.Assert.assertTrue;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;

import org.junit.Test;

import com.cnfantasia.server.common.cryption.CryptConstant;
import com.cnfantasia.server.common.exception.CryptRuntimeException;

/**
 * Digital Signature Algorithm (DSA)是Schnorr和ElGamal签名算法的变种，
 * 被美国NIST作为DSS(DigitalSignature Standard)
 * DSA是基于整数有限域离散对数难题的，其安全性与RSA相比差不多。
 * DSA的一个重要特点是两个素数公开，这样，当使用别人的p和q时，
 * 即使不知道私钥，你也能确认它们是否是随机产生的，还是作了手脚。
 * RSA算法却作不到。
 * @author syl
 * @version 1.0
 * @since 1.0
 */
public class DSACrypt{
  public static final String ALGORITHM = CryptConstant.Crypt_DSA;

//  /**
//   * 默认密钥字节数
//   * DSA  
//   * Default Keysize 1024   
//   * Keysize must be a multiple of 64, ranging from 512 to 1024 (inclusive).
//   */
//  private static final int KEY_SIZE = 1024;

  /**
   * 用私钥对信息生成数字签名
   * @param data 加密数据
   * @param privateKey 私钥
   * @return
   */
  public static byte[] getSign(byte[] data, PrivateKey privateKey) {
    byte[]  resData = null;
    try {
      // 用私钥对信息生成数字签名
      Signature signature = Signature.getInstance(ALGORITHM);
      signature.initSign(privateKey);
      signature.update(data);
      resData=signature.sign();
    } catch (Exception e) {
      throw new CryptRuntimeException(e);
    }
    return resData;
  }

  /**
   * 校验数字签名
   * 
   * @param data 加密数据
   * @param publicKey 公钥
   * @param sign  数字签名
   * @return 校验成功返回true 失败返回false
   */
  public static boolean verifySign(byte[] data, PublicKey publicKey, byte[] sign){
    boolean resData=false;
    try {
      Signature signature = Signature.getInstance(ALGORITHM);
      signature.initVerify(publicKey);
      signature.update(data);
      // 验证签名是否正常
      resData=signature.verify(sign);
    } catch (Exception e) {
      throw new CryptRuntimeException(e);
    }
    return resData;
  }

  /**
   * 生成密钥
   * 
   * @param seed 种子
   * @return 密钥对象
   */
  public static KeyPair getKeyPair(byte[] seed,int keySize){
    KeyPairGenerator keygen=null;
    try {
      keygen = KeyPairGenerator.getInstance(ALGORITHM);
    } catch (NoSuchAlgorithmException e) {
      throw new CryptRuntimeException(e);
    }
    // 初始化随机产生器
    SecureRandom secureRandom = new SecureRandom();
    secureRandom.setSeed(seed);
    keygen.initialize(keySize, secureRandom);
    KeyPair keyPair = keygen.genKeyPair();
    return keyPair;
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
  
  @Test
  public static void main(String[] args) throws Exception {
    String default_seed = "0f22507a10bbddd07d8a3082122966e3";
    int keySize = 1024;
    
    String inputStr = "abc";
    byte[] data = inputStr.getBytes();
    
    // 构建密钥    KeyPair keyPair = getKeyPair(default_seed.getBytes(),keySize);

    // 获得密钥
    PublicKey publicKey =(PublicKey)getPublicKey(keyPair);
    PrivateKey privateKey =(PrivateKey)getPrivateKey(keyPair);

    System.err.println("公钥:\r" + publicKey);
    System.err.println("私钥:\r" + privateKey);

    // 产生签名
    byte[] sign = DSACrypt.getSign(data, privateKey);
    System.err.println("签名:\r" + new String(sign));

    // 验证签名
    boolean status = DSACrypt.verifySign(data, publicKey, sign);
    System.err.println("状态:\r" + status);
    assertTrue(status);
  }
  
}