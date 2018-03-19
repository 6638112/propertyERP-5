/**   
 * Filename:    EncryptUtil.java   
 * @version:    1.0  
 * Create at:   2016年2月19日 下午1:29:11   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2016年2月19日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.commbusi.encrypt.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.cnfantasia.server.commbusi.encrypt.constant.EncryptConstant;

/**
 * Filename: EncryptUtil.java
 * 
 * @version: 1.0.0 Create at: 2016年2月19日 下午1:29:11 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2016年2月19日 shiyl 1.0 1.0 Version
 */
public class EncryptUtil {
	
	@SuppressWarnings({"rawtypes"})
	public static Map<String, String> getRequestMap(Map requestParams) {
		Map<String, String> params = new HashMap<String, String>();
		{
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// try {
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				// } catch (UnsupportedEncodingException e) {
				// logger.info(valueStr+" .getBytes cause exception,", e);
				// }
				params.put(name, valueStr);
			}
		}
		return params;
	}
	
	/**
	 * 除去数组中的空值和签名参数
	 * @param sArray 签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static Map<String, String> paraFilter(Map<String, String> sArray) {
		Map<String, String> result = new HashMap<String, String>();
		if (sArray == null || sArray.size() <= 0) {
			return result;
		}
		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase(EncryptConstant.PARAM_TOKEN) || key.equalsIgnoreCase(EncryptConstant.PARAM_SIGN_TYPE)) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}
	
	
	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * @param params 需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}
	
	public static String createLinkString(String url, Map<String, String> params) {
		String prestr = url;
		if(params!=null&&params.size()>0){
			prestr += "?";
		}
		prestr += createLinkString(params);
		return prestr;
	}
	
	public static String md5Hex(String srcStr){
		return DigestUtils.md5Hex(srcStr);
	}
	
	public static String sha256Hex(String srcStr){
		return DigestUtils.sha256Hex(srcStr);
	}
	
}
