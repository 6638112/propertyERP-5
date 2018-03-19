/**   
 * Filename:    SessionCryptUtil.java   
 * @version:    1.0  
 * Create at:   2014年6月10日 上午11:31:29   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年6月10日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.business.pub.session;

import java.io.UnsupportedEncodingException;

import javax.crypto.SecretKey;

import com.cnfantasia.server.business.pub.utils.ByteHexConvertUtil;
import com.cnfantasia.server.common.cryption.BaseCryptUtil;
import com.cnfantasia.server.common.cryption.CryptConstant;
import com.cnfantasia.server.common.cryption.oneWay.HmacMD5Crypt;
import com.cnfantasia.server.common.cryption.oneWay.MD5Crypt;
import com.cnfantasia.server.common.cryption.symmetry.DESedeCrypt;

/**
 * Filename: SessionCryptUtil.java
 * 
 * @version: 1.0.0 Create at: 2014年6月10日 上午11:31:29 Description:
 *           Session加密解密处理工具类
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年6月10日 shiyl 1.0 1.0 Version
 */
public class SessionCryptUtil {
	// private static final String DEFAULT_ENCODE = "ISO-8859-1";
	private static final String DEFAULT_ENCODE = "UTF-8";

	public static void main(String[] args) throws UnsupportedEncodingException {
		String accNo = "15311111111";
		String time = "2014-6-11 12:05";
		Integer accType = 1;
		String sessionKey = generateSessionKey(accNo, accType, time);
		System.out.println(sessionKey);
	}

	/**
	 * 加密过程
	 * 
	 * @param accNo
	 * @param password
	 * @param time
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String generateSessionKey(String accNo, Integer accType, String time)
			throws UnsupportedEncodingException {
		StringBuffer sbf = new StringBuffer();
		String srcData = sbf.append(accNo).append(";").append(accType).append(";").append(time).append(";").toString();
		byte[] resData = srcData.getBytes(DEFAULT_ENCODE);
//		{//RSA,time+accNo+accType作为seed
//			byte[] seed = (time+accNo+accType).getBytes(DEFAULT_ENCODE);
//			KeyPair keyPair=RSACrypt.getKeyPair(seed, 1024);
//			PrivateKey privateKey=keyPair.getPrivate();
//			resData=RSACrypt.encrypt(resData, privateKey);
//		}
		{//DESedeCrypt,accNo+time+accType作为seed
			byte[] seed = (accNo+time+accType).getBytes(DEFAULT_ENCODE);
			int keySize=168;
			SecretKey desedekey=BaseCryptUtil.getKey(CryptConstant.Crypt_DESede, seed, keySize);
			resData = DESedeCrypt.encrypt(resData, desedekey);
		}
		{// MD5
			resData = MD5Crypt.encrypt(resData);
		}
		{//HmacMD5Crypt,accNo+time+accType作为seed
			byte[] seed = (accNo+time+accType).getBytes(DEFAULT_ENCODE);
			int keySize=168;
			SecretKey desedekey=BaseCryptUtil.getKey(CryptConstant.Crypt_DESede, seed, keySize);
			resData = HmacMD5Crypt.encrypt(resData, desedekey);
		}
		return ByteHexConvertUtil.bytesToHexString(resData);
		// return new String(resData,DEFAULT_ENCODE);
	}

	
}
