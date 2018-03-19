package com.cnfantasia.wl.wechat.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.wl.wechat.WeChatConstant;

/**
 * 微信 access_token 获取器
 * 
 * @author wenfq
 * 
 */
public class AccessTokenGetter {

	/**
	 * 获得ACCESS_TOKEN
	 * 
	 * @Title: getAccess_token
	 * @Description: 获得ACCESS_TOKEN
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getAccess_token() {
		String accessToken = RedisCacheHandler.get(RedisCachePrefix.WECHAT_ACCESS_TOKEN + WeChatConfig.APPID);
		//String accessToken = "7LaWZe1MbxWC13pwayCW6M6iQ5xJDQjm1UFYaRgpQJur_pi8PJezruNGjNUy27iOWPR9DQYAPF_shGlGe5gpRlhWxO1C404aIY2iqLsQQ0IZBwdfbt8E3i3K7r0aq3o5YOJhAFATJE";
		if (!StringUtils.isEmpty(accessToken)) {
			return accessToken;
		}

		try {
			URL urlGet = new URL(WeChatConstant.AccessTokenGetterURL);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

			http.setRequestMethod("GET"); //必须是get方式请求    
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒 
			http.connect();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			accessToken = JSON.parseObject(message).getString("access_token");

			RedisCacheHandler.set(RedisCachePrefix.WECHAT_ACCESS_TOKEN + WeChatConfig.APPID, accessToken, 7200-200);
			
			System.out.println("accessToken is :" + accessToken);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accessToken;
	}

	/**
	 * 刷新AccessToken
	 * 
	 * @return
	 */
	public static String refreshAccessToken() {
		RedisCacheHandler.del(RedisCachePrefix.WECHAT_ACCESS_TOKEN + WeChatConfig.APPID);
		return getAccess_token();
	}
	
	/**
	 * 从正式服务器上获得ACCESS_TOKEN
	 */
	public static String getAccess_token_remoteServer() {
		String accessToken = null;
		try {
			URL urlGet = new URL("http://www.jiefangqu.com/LA/wechat/getAccessToken.html");
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

			http.setRequestMethod("GET"); //必须是get方式请求    
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒 
			http.connect();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			accessToken = JSON.parseObject(message).getString("AccessToken");

			System.out.println("accessToken is :" + accessToken);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accessToken;
	}
}
