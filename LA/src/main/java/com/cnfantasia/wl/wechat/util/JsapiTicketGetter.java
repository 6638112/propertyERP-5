package com.cnfantasia.wl.wechat.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.wl.wechat.WeChatConstant;

public class JsapiTicketGetter {

	private static String ticket = null;

	/**
	 * 下次更新时间点, accessToken只有2小时的有效性，要定时去刷新获取
	 */
	private static long validTimeTo = System.currentTimeMillis();

	/**
	 * 获得ACCESS_TOKEN
	 * 
	 * @Title: getAccess_token
	 * @Description: 获得ACCESS_TOKEN
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getJsapiTicket() {
		if (ticket != null && System.currentTimeMillis() < validTimeTo) {
			return ticket;
		}

		try {
			URL urlGet = new URL(String.format(WeChatConstant.JSAPITicketGetterURL, AccessTokenGetter.getAccess_token()));
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
			ticket = JSON.parseObject(message).getString("ticket");

			System.out.println("ticket is :" + ticket);
		} catch (Exception e) {
			e.printStackTrace();
		}

		validTimeTo = System.currentTimeMillis() + WeChatConstant.ExpireTime;
		return ticket;
	}
}
