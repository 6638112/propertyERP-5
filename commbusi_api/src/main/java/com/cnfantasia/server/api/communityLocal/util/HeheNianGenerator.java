/**   
* Filename:    SecretGenerator.java   
* @version:    1.0  
* Create at:   2014年10月31日 上午8:05:10   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communityLocal.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import com.cnfantasia.server.common.utils.StringUtils;


/**
 * Filename:    SecretGenerator.java
 * @version:    1.0.0
 * Create at:   2014年10月31日 上午8:05:10
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月31日       shiyl             1.0             1.0 Version
 */
public class HeheNianGenerator {
	public static final String secret = "WFVCXA#$%A%LIJ$";
	public static final String via = "jfq";
	//via=jfq&uid=2&username=test1&mobile=15912333300&realName=&idNo=&email=&sign=D62B17F21C587B8E2B537DB96C8AF03A
	public static void main(String[] args) throws UnsupportedEncodingException {
		String goalUrl = "http://www.hehenian.com/appcomm/app-index.do?";
		String userId = "50001";
		if(!StringUtils.isEmpty(userId)){
			String resUrl = HeheNianGenerator.getGoalUrl(goalUrl, HeheNianGenerator.secret, HeheNianGenerator.via, userId.toString(), null, null, null, null, null);
			System.out.println(resUrl);
		}
		
//		System.out.println(getSign("WFVCXA#$%A%LIJ$", "jfq", "2", "test1", "15912333300", null, null, null));
//		System.out.println(getGoalUrl("http://www.hehenian.com/appcomm/app-index.do?","WFVCXA#$%A%LIJ$", "jfq", "668", "xiaoming", "15648579859",null, null, null));
////		System.out.println(getGoalUrl("http://www.hehenian.com/appcomm/app-index.do?","WFVCXA#$%A%LIJ$", "jfq", "666", "test1", "15341212141",null, null, null));
//		StringBuffer sbf = new StringBuffer();
//		sbf.append(secret);
//		sbf.append("via");
//		sbf.append("jfq");
//		sbf.append("uid");
//		sbf.append("2");
//		sbf.append("username");
//		sbf.append("test1");
//		sbf.append("mobile");
//		sbf.append("15912333300");
//		sbf.append("realName");
//		sbf.append("");
//		sbf.append("idNo");
//		sbf.append("");
//		sbf.append("email");
//		sbf.append("");
//		sbf.append(secret);
//		String srcStr = sbf.toString();
//		String res =MD5Util.MD5Encode(srcStr, "UTF-8").toUpperCase();
//		System.out.println(res);
	}
	public static String getGoalUrl(String baseUrl,String secret,String via,String uid,String username,String mobile,String realName,String idNo,String email){
		StringBuffer sbf = new StringBuffer();
		sbf.append(baseUrl);
		sbf.append("via=");sbf.append(via);
		sbf.append("&uid=");sbf.append(uid);
		sbf.append("&username=");sbf.append(username==null?"":username);
		sbf.append("&mobile=");sbf.append(mobile==null?"":mobile);
		sbf.append("&realName=");
		sbf.append(getRealName(realName));
		sbf.append("&idNo=");sbf.append(idNo==null?"":idNo);
		sbf.append("&email=");sbf.append(email==null?"":email);
		sbf.append("&sign=");sbf.append(getSign(secret, via, uid, username, mobile, realName, idNo, email));
		return sbf.toString();
	}
	public static String getSign(String secret,String via,String uid,String username,String mobile,String realName,String idNo,String email) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(secret);
		sbf.append("via");
		sbf.append(via);
		sbf.append("uid");
		sbf.append(uid);
		sbf.append("username");
		sbf.append(username==null?"":username);
		sbf.append("mobile");
		sbf.append(mobile==null?"":mobile);
		sbf.append("realName");
		sbf.append(getRealName(realName));
		sbf.append("idNo");
		sbf.append(idNo==null?"":idNo);
		sbf.append("email");
		sbf.append(email==null?"":email);
		sbf.append(secret);
		String srcStr = sbf.toString();
		String res =MD5Encode(srcStr, "UTF-8").toUpperCase();
		return res;
	}
	
	public static String getRealName(String realName){
		String res = "";
//		try {
//			res = (realName==null?"":URLEncoder.encode(realName,"UTF-8"));
//		} catch (Exception e) {
//		}
		return res;
	}
	
	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = origin;
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
}
