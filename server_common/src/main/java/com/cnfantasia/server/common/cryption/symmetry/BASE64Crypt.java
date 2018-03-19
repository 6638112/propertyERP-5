package com.cnfantasia.server.common.cryption.symmetry;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.cnfantasia.server.common.exception.CryptRuntimeException;

/**
 * Base64是一種使用64基的位置計數法。它使用2的最大次方來代表僅可列印的ASCII 字元。    
 * 這使它可用來作為電子郵件的傳輸編碼。在Base64中的變數使用字元A-Z、a-z和0-9 ，    
 *這樣共有62個字元，用來作為開始的64個數字，最後兩個用來作為數字的符號在不同的    
 * 系統中而不同。
 * 
 * @version 1.00
 * @author syl
 * 
 */
public class BASE64Crypt {
  /**
   * BASE64解密
   * 
   * @param key
   * @return
   */
	public static byte[] decrypt(String data){
    byte[] resData=null;
    try {
      resData= (new BASE64Decoder()).decodeBuffer(data);
    } catch (IOException e) {
      throw new CryptRuntimeException(e);
    }
    return resData;
  }

  /**
   * BASE64加密
   * 
   * @param key
   * @return
   * @throws Exception
   */
  public static String encrypt(byte[] data){
    String resData = (new BASE64Encoder()).encodeBuffer(data);
    return resData;
  }
}
