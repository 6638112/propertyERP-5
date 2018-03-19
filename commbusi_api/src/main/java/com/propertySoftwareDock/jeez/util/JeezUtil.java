package com.propertySoftwareDock.jeez.util;

import com.propertySoftwareDock.jeez.entity.RSAKeyParamEntity;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;

/**
 * @ClassName: JeezUtil
 * @Date: 2016-11-21 13:55
 * @Auther: kangduo
 * @Description:(用来处理极致对接的工具类)
 */
public class JeezUtil {

    //字符串与字符数组互转编码
    private static final Charset defaultCharset = Charset.forName("UnicodeLittleUnmarked");
    //RSA最大加密明文大小
    private static final int MAX_ENCRYPT_BLOCK = 117;
    //RSA最大解密密文大小
    private static final int MAX_DECRYPT_BLOCK = 128;
    //RSA密钥加解密方式
    private static final String ALGORITHM = "RSA";

    /**
     * 获取密钥对
     *
     * @param publicKeyStr
     * @param privateKeyStr
     * @return
     */
    public static KeyPair getRSAKeyPair(String publicKeyStr, String privateKeyStr) {
        try {
            //publicKey
            RSAKeyParamEntity keyEntity = getKeyEntity(publicKeyStr);
            byte[] m = Base64.decode(keyEntity.getModulus());
            byte[] e = Base64.decode(keyEntity.getExponent());
            BigInteger pubMudulus = new BigInteger(1, m);
            BigInteger pubExponent = new BigInteger(1, e);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(pubMudulus, pubExponent);

            //privateKey
            keyEntity = getKeyEntity(privateKeyStr);
            BigInteger modulus = new BigInteger(1, Base64.decode(keyEntity.getModulus()));
            BigInteger publicExponent = new BigInteger(1, Base64.decode(keyEntity.getExponent()));
            BigInteger privateExponent = new BigInteger(1, Base64.decode(keyEntity.getD()));
            BigInteger primeP = new BigInteger(1, Base64.decode(keyEntity.getP()));
            BigInteger primeQ = new BigInteger(1, Base64.decode(keyEntity.getQ()));
            BigInteger primeExponentP = new BigInteger(1, Base64.decode(keyEntity.getDp()));
            BigInteger primeExponentQ = new BigInteger(1, Base64.decode(keyEntity.getDq()));
            BigInteger crtCoefficient = new BigInteger(1, Base64.decode(keyEntity.getInverseQ()));
            RSAPrivateCrtKeySpec privateCrtKeySpec = new RSAPrivateCrtKeySpec(modulus, publicExponent,
                    privateExponent, primeP, primeQ, primeExponentP, primeExponentQ, crtCoefficient);

            return new KeyPair(keyFactory.generatePublic(publicKeySpec), keyFactory.generatePrivate(privateCrtKeySpec));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过私钥拿到值
     *
     * @param encrypt
     * @param unEncrypt
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String getValueByPrivateKey(String encrypt, String unEncrypt, PrivateKey privateKey) throws Exception {
        byte[] tmp = Base64.decode(encrypt);
        String after = RSADecode(tmp, privateKey, defaultCharset);
        after = unEncrypt == null ? after : after + unEncrypt;
        byte[] res = Base64.decode(after);
        return res == null ? null : new String(res, defaultCharset);
    }

    public static String getValueByPublicKey(String dataToEncrypt, PublicKey publicKey) throws Exception {
        byte[] b = dataToEncrypt.getBytes(defaultCharset);
        return Base64.encode(RSAEncode(b, publicKey));
    }

    /**
     * RSA公钥加密
     *
     * @param plainText 待加密字符串
     * @param key 公钥
     * @return
     * @throws Exception
     */
    private static byte[] RSAEncode(final byte[] b, PublicKey key) throws Exception {

        int inputLen = b.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(b, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(b, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * RSA私钥解密
     *
     * @param b 待解密字节数组
     * @param key 私钥
     * @param charset 转化成字符串要的编码
     * @return 解密后的字符串
     * @throws Exception
     */
    private static String RSADecode(final byte[] b, PrivateKey key, Charset charset) throws Exception {
        int inputLen = b.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(b, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(b, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();

        return new String(decryptedData, charset);
    }

    /**
     * 得到密钥参数对象
     *
     * @param keyStr
     * @return
     * @throws DocumentException
     */
    private static RSAKeyParamEntity getKeyEntity(String keyStr) throws DocumentException {
        Document doc = DocumentHelper.parseText(keyStr);
        String modulus = getElementValue(doc, "Modulus");
        String exponent = getElementValue(doc, "Exponent");
        String p = getElementValue(doc, "P");
        String q = getElementValue(doc, "Q");
        String d = getElementValue(doc, "D");
        String dp = getElementValue(doc, "DP");
        String dq = getElementValue(doc, "DQ");
        String inverseQ = getElementValue(doc, "InverseQ");
        return new RSAKeyParamEntity(modulus, exponent, p, q, d, dp, dq, inverseQ);
    }

    /**
     * 获取指定节点的值
     *
     * @param doc
     * @param elementName
     * @return
     */
    public static String getElementValue(Document doc, String elementName) {
        return doc.getRootElement().element(elementName) == null ?
                null : doc.getRootElement().element(elementName).getStringValue();
    }

}
