/**   
* Filename:    RoomInfoEncodeUtil.java   
* @version:    1.0  
* Create at:   2014年12月22日 上午3:44:10   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.room.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.room.entity.SimpleRoomInfo;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    RoomInfoEncodeUtil.java
 * @version:    1.0.0
 * Create at:   2014年12月22日 上午3:44:10
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月22日       shiyl             1.0             1.0 Version
 */
public class RoomInfoEncodeUtil {
	private static Log logger = LogFactory.getLog(RoomInfoEncodeUtil.class);
//	private static BigInteger innerPrivateKey = new BigInteger("08723975391");
	public static void main(String[] args) {
		String roomInfo = encode(new BigInteger("2222531251"), "2014-12-22 11:51:57",new BigInteger("08723975391"));
		System.out.println(roomInfo);
		SimpleRoomInfo resData = decode(roomInfo,new BigInteger("08723975391"));
		System.out.println(resData.getNowTime());
		System.out.println(resData.getRoomId());
	}
	
	//加密方式有待增强
	public static String encode(BigInteger roomId,String nowTime,BigInteger inviteUserId){
		String roomInfo = null;
		Map<String,Object> roomInfoMap = new HashMap<String, Object>();
		roomInfoMap.put("roomId", roomId);
		roomInfoMap.put("nowTime", nowTime);
		roomInfo =  JSON.toJSONString(roomInfoMap);
		try {
			roomInfo = doEncrypt(roomInfo, inviteUserId);
		} catch (UnsupportedEncodingException e) {
			logger.debug("RoomInfoEncodeUtil.encode.cause exception,roomId is "+roomId+",nowTime is "+nowTime+",userId is "+inviteUserId,e);
		}
		return roomInfo;
	}
	
	@SuppressWarnings("unchecked")
	public static SimpleRoomInfo decode(String roomInfo,BigInteger inviteUserId){
		SimpleRoomInfo resData = null;
		if(!StringUtils.isEmpty(roomInfo)){
			try {
				roomInfo = doDecrypt(roomInfo, inviteUserId);
				Map<String,Object> roomInfoMap = null;
				roomInfoMap = JSON.parseObject(roomInfo, Map.class);
				BigInteger roomId = new BigInteger(roomInfoMap.get("roomId").toString());
				String nowTime = (String)roomInfoMap.get("nowTime");
				resData = new SimpleRoomInfo(roomId, nowTime);
			} catch (Exception e) {
				logger.debug("RoomInfoEncodeUtil.decode.cause exception,roomInfo is "+roomInfo+",userId is "+inviteUserId,e);
			}
		}
		return resData;
	}
	
	/**
	 * 加密过程
	 */
	private static String doEncrypt(String baseData,BigInteger userId) throws UnsupportedEncodingException{
//		StringBuffer sbf = new StringBuffer();
//		String srcData = sbf.append(baseData).toString();
//		byte[] resData =srcData.getBytes("ISO-8859-1");
////		{//RSA,userId+""作为seed
////			byte[] seed = (userId+"").getBytes("ISO-8859-1");
////			KeyPair keyPair=RSACrypt.getKeyPair(seed, 512);
////			PrivateKey privateKey=keyPair.getPrivate();
////			resData=RSACrypt.encrypt(resData, privateKey);
////		}
////		{//DESedeCrypt,userId+""作为seed
////			byte[] seed = (userId+"").getBytes("ISO-8859-1");
////			int keySize=168;
////			SecretKey desedekey=BaseCryptUtil.getKey(CryptConstant.Crypt_DESede, seed, keySize);
////			resData = DESedeCrypt.encrypt(resData, desedekey);
////		}
//		return ByteHexConvertUtil.bytesToHexString(resData);
		
		return baseData;
	}
	
	/**
	 * 解密过程
	 */
	private static String doDecrypt(String baseData,BigInteger userId) throws UnsupportedEncodingException{
//		byte[] resData =ByteHexConvertUtil.hexStringToBytes(baseData);
////		{//DESedeCrypt,userId+""作为seed
////			byte[] seed = (userId+"").getBytes("ISO-8859-1");
////			int keySize=168;
////			SecretKey desedekey=BaseCryptUtil.getKey(CryptConstant.Crypt_DESede, seed, keySize);
////			resData = DESedeCrypt.decrypt(resData, desedekey);
////		}
////		{//RSA,userId+""作为seed
////			byte[] seed = (userId+"").getBytes("ISO-8859-1");
////			KeyPair keyPair=RSACrypt.getKeyPair(seed, 512);
////			PublicKey publicKey=keyPair.getPublic();
////			resData=RSACrypt.decrypt(resData, publicKey);
////		}
//		String resStr = new String(resData,"ISO-8859-1");
//		return resStr;
		
		return baseData;
	}
	
}
