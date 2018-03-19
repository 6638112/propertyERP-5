package com.cnfantasia.server.api.payment.util;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.crypto.Cipher;

import com.alibaba.fastjson.JSONObject;
import com.alipay.sign.Base64;
import com.cnfantasia.server.api.payment.constant.PayConfigConstant;


/**
 * <p>
 * RSA公钥/私钥/签名工具包
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 * 
 * @author IceWee
 * @version 1.0
 */
public final class SqPayRSAUtils {

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";
    
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    
    /**
     * 用私钥对信息生成数字签名
     * @param data 签名数据
     * @param privateKey 私钥
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateK);
        signature.update(data);
        return Base64.encode(signature.sign());
    }
    
    /**
     * 校验数字签名
     * @param data 已签名数据
     * @param publicKey 公钥
     * @param sign 签名前数据
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        byte[] keyBytes = Base64.decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64.decode(sign));
    }
    
    /**
     * 私钥解密
     * @param encryptedData 已加密数据
     * @param privateKey 私钥
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }


    /**
     * 公钥加密
     * @param data 源数据
     * @param publicKey 公钥
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }
    
    public static void main(String[] args) {
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		
		map.put("MerNo",  "10000");
		map.put("OrderState", "0009");
		map.put("Amount","2.0");
		map.put("UserNo", "50002");
		map.put("PayAmount","1.5");
		map.put("StateExplain", "交易结果未知");
		map.put("Remark", "{老王家物业}粤C66666，2017-01-23至2017-02-23[停车费]");
		map.put("DiscountAmount","0.5");
		map.put("BillNo", "2016102015415180982");
		
		String data = JSONObject.toJSONString(map);
		System.err.println(data);
		boolean signFlag = false;
		try {
			byte[] dataBytes = data.getBytes(PayConfigConstant.CHARTSET_UTF_8);
			String sign = "OZg/bMMJSX2Ytn0fdYzzLRNxfJ7ok1e/LZsxOLPly8bzqD98hUPsfs3aJ3PEKsEUvwG9xfSRgdRV\r\nilt8A7GhCSjXPzMnbMS9yWHNvcaY7+zc9CVPOt/Q/lEY2g1BnoCcWJz8QyQd7qSTVBk5LEIUsWL6\r\n1IKZp9gxXbT9Z9ECQKQ=";
			String sqPubicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCemkSe6YQ1y1J//G5Fc5yhoj3zobOumhAOKxAOiyo5DdUZaVzNVyx+a7AsEGG2S2GuZejb2cHUBAUH1Ylm90nFhQTPsh9MiUbVPNh4lZpHCt/10DHmVkByx+nfopegQxOa+5birJyBcTGNyFKpHJxGYsBnLr+Qm4rmbtqHh95D5wIDAQAB";
			// 签名校验
			signFlag = SqPayRSAUtils.verify(dataBytes, sqPubicKey, sign);
			System.err.println(signFlag);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
