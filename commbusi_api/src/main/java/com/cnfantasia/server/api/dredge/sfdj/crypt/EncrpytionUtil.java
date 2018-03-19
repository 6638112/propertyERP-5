package com.cnfantasia.server.api.dredge.sfdj.crypt;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加密相关工具类
 * 
 * @author fengli
 * @date 2015年5月30日 上午10:15:43
 */
public class EncrpytionUtil {

    private static final Logger logger = LoggerFactory
            .getLogger(EncrpytionUtil.class);

    public static final int CHAR_UPPERCASE = 1;
    public static final int CHAR_LOWERCASE = 0;

    /**
     * 通过AES算法加密
     * 
     * @param data
     * @param secretKey
     * @return
     */
    public static byte[] encryptByAES(byte[] data, byte[] secretKey) {
        try {
            byte[] result = null;
            SecretKeySpec skey = new SecretKeySpec(processAesKey(secretKey),
                    "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            result = cipher.doFinal(data);
            return result;
        } catch (Exception e) {
            logger.info("AES加密异常", e);
        }
        return null;
    }

    /**
     * 通过AES算法解密
     * 
     * @param data
     * @param secretKey
     * @return
     */
    public static byte[] decryptByAES(byte[] data, byte[] secretKey) {
        try {
            byte[] result = null;
            SecretKeySpec skey = new SecretKeySpec(processAesKey(secretKey),
                    "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            result = cipher.doFinal(data);
            return result;
        } catch (Exception e) {
            logger.info("AES解密异常", e);
        }
        return null;
    }

    /**
     * 通过HMAC-MD5算法生成摘要
     * 
     * @param data
     * @param secretKey
     * @return
     */
    public static byte[] hmacmd5(byte[] data, byte[] secretKey) {
        int length = 64;
        byte[] ipad = new byte[length];
        byte[] opad = new byte[length];
        for (int i = 0; i < 64; i++) {
            ipad[i] = 0x36;
            opad[i] = 0x5C;
        }
        byte[] actualKey = secretKey;
        byte[] keyArr = new byte[length];

        if (secretKey.length > length) {
            actualKey = md5(secretKey);
        }
        for (int i = 0; i < actualKey.length; i++) {
            keyArr[i] = actualKey[i];
        }

        if (actualKey.length < length) {
            for (int i = actualKey.length; i < keyArr.length; i++)
                keyArr[i] = 0x00;
        }

        byte[] kIpadXorResult = new byte[length];
        for (int i = 0; i < length; i++) {
            kIpadXorResult[i] = (byte) (keyArr[i] ^ ipad[i]);
        }

        byte[] firstAppendResult = new byte[kIpadXorResult.length + data.length];
        for (int i = 0; i < kIpadXorResult.length; i++) {
            firstAppendResult[i] = kIpadXorResult[i];
        }
        for (int i = 0; i < data.length; i++) {
            firstAppendResult[i + keyArr.length] = data[i];
        }

        byte[] firstHashResult = md5(firstAppendResult);

        byte[] kOpadXorResult = new byte[length];
        for (int i = 0; i < length; i++) {
            kOpadXorResult[i] = (byte) (keyArr[i] ^ opad[i]);
        }

        byte[] secondAppendResult = new byte[kOpadXorResult.length
                + firstHashResult.length];
        for (int i = 0; i < kOpadXorResult.length; i++) {
            secondAppendResult[i] = kOpadXorResult[i];
        }
        for (int i = 0; i < firstHashResult.length; i++) {
            secondAppendResult[i + keyArr.length] = firstHashResult[i];
        }

        byte[] hmacMd5Bytes = md5(secondAppendResult);
        return hmacMd5Bytes;
    }

    /**
     * hmac-md算法，直接返回字符串，默认大写
     * 
     * @param data
     * @param secretKey
     * @param encoding
     * @return
     */
    public static String hmacmd5(String data, String secretKey, String encoding) {
        if (encoding == null) {
            encoding = "UTF-8";
        }
        try {
            byte[] dataBytes = data.getBytes(encoding);
            byte[] secretKeyBytes = secretKey.getBytes(encoding);
            byte[] result = hmacmd5(dataBytes, secretKeyBytes);
            return bytesToHexString(result, CHAR_LOWERCASE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * MD5摘要
     * 
     * @param data
     * @return
     */
    public static byte[] md5(byte[] data) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(data);
            return md5.digest();
        } catch (Exception e) {
            logger.info("MD5生成摘要异常", e);
        }
        return null;
    }

    /**
     * 字节码转换成16进制字符串
     * 
     * @param data
     * @param charCase
     * @return
     */
    public static String bytesToHexString(byte[] data, int charCase) {
        StringBuilder result = new StringBuilder("");
        if (data == null || data.length <= 0) {
            return null;
        }
        for (int i = 0; i < data.length; i++) {
            int v = data[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                result.append(0);
            }
            result.append(hv);
        }
        // 默认大写
        if (charCase == CHAR_LOWERCASE) {
            return result.toString().toLowerCase();
        } else {
            return result.toString().toUpperCase();
        }
    }

    /**
     * 16进制字符串转换成字节码
     * 
     * @param data
     * @return
     */
    public static byte[] hexStringToBytes(String data) {
        if (data == null || data.equals("")) {
            return null;
        }
        data = data.toUpperCase();
        int length = data.length() / 2;
        char[] hexChars = data.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * 得到字符的十进制
     * 
     * @param c
     * @return
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(Character.toUpperCase(c));
    }

    /**
     * 处理AES key，默认后面补0，超出直接截取
     * 
     * @param key
     * @return
     */
    private static byte[] processAesKey(byte[] key) {
        byte[] result = new byte[16];
        if (key != null && key.length == 16) {
            return key;
        } else if (key != null && key.length > 0) {
            int i = 0;
            for (byte item : key) {
                result[i++] = item;
            }
            return result;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(bytesToHexString(
                hmacmd5("test".getBytes(), processAesKey("123456".getBytes())),
                1));
    }
}
