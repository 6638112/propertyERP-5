package com.cnfantasia.server.common.cryption.dissymmetry;

import static org.junit.Assert.assertTrue;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.junit.Test;

import com.cnfantasia.server.common.cryption.symmetry.BASE64Crypt;
import com.cnfantasia.server.common.exception.CryptRuntimeException;

/**
 * 依赖于DSACrypt类
 * @author syl
 * @version 1.0
 * @since 1.0
 */
public class DSABase64Crypt{

  /**
   * 用私钥对信息生成数字签名
   * @param data 加密数据
   * @param privateKey 私钥
   * @return
   */
  public static String getSign(byte[] data, String privateKey) {
    String  resData = null;
    try {
      // 解密由base64编码的私钥
      byte[] keyBytes = BASE64Crypt.decrypt(privateKey);
      // 构造PKCS8EncodedKeySpec对象
      PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
      // KEY_ALGORITHM 指定的加密算法
      KeyFactory keyFactory = KeyFactory.getInstance(DSACrypt.ALGORITHM);
      
      // 取私钥匙对象
      PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
      resData=BASE64Crypt.encrypt(DSACrypt.getSign(data, priKey));
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
   * 
   */
  public static boolean verifySign(byte[] data, String publicKey, String sign){
    // 解密由base64编码的公钥
    byte[] keyBytes = BASE64Crypt.decrypt(publicKey);
    // 构造X509EncodedKeySpec对象
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    PublicKey pubKey = null;
    try {
      // ALGORITHM 指定的加密算法
      KeyFactory keyFactory = KeyFactory.getInstance(DSACrypt.ALGORITHM);
      // 取公钥匙对象
      pubKey = keyFactory.generatePublic(keySpec);
    } catch (Exception e) {
      throw new CryptRuntimeException(e);
    }
    // 验证签名是否正常
    return DSACrypt.verifySign(data, pubKey, BASE64Crypt.decrypt(sign));
  }

  /**
   * 生成密钥
   * 
   * @param seed 种子
   * @return 密钥对象
   */
  public static KeyPair getKeyPair(byte[] seed,int keySize){
    return DSACrypt.getKeyPair(seed, keySize);
  }


  /**
   * 取得私钥
   * @param keyPair
   * @return
   */
  public static String getPrivateKey(KeyPair keyPair){
    return BASE64Crypt.encrypt(DSACrypt.getPrivateKey(keyPair).getEncoded());
  }

  /**
   * 取得公钥
   * @param keyPair
   * @return
   */
  public static String getPublicKey(KeyPair keyPair){
    return BASE64Crypt.encrypt(DSACrypt.getPublicKey(keyPair).getEncoded());
  }

  @Test
  public static void main(String[] args) throws Exception {
    String default_seed = "0f22507a10bbddd07d8a3082122966e3";
    int keySize = 1024;
    
    String inputStr = "abc";
    byte[] data = inputStr.getBytes();
    
    // 构建密钥    KeyPair keyPair = getKeyPair(default_seed.getBytes(),keySize);

    // 获得密钥
    String publicKey =getPublicKey(keyPair);
    String privateKey =getPrivateKey(keyPair);

    System.err.println("公钥:\r" + publicKey);
    System.err.println("私钥:\r" + privateKey);

    // 产生签名
    String sign = DSABase64Crypt.getSign(data, privateKey);
    System.err.println("签名:\r" + sign);

    // 验证签名
    boolean status = DSABase64Crypt.verifySign(data, publicKey, sign);
    System.err.println("状态:\r" + status);
    assertTrue(status);
  }
  
}