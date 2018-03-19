package com.cnfantasia.wl.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;

public class UserInfoGetter {
	
	public static String getUserInfo(String openId) {
		try {
			String getUserInfoURL = String.format(WeChatConstant.UserInfoGetterURL, AccessTokenGetter.getAccess_token(), openId);
			URL urlGet = new URL(getUserInfoURL);
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
			String jsonMessage = new String(jsonBytes, "UTF-8");
			return jsonMessage;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("get wechat user info failure", e);
		}
	}

	/**
	 * 获取微信用户信息
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static SnsUserinfo getSnsUserInfo() throws ClientProtocolException, IOException {
		String requestURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&"
				+ "redirect_uri=http%3a%2f%2fwww.jiefangqu.cn%2fwlLightApp%2fwxlogin%2fwechatAuth2.html"
				+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		requestURL = requestURL.replace("APPID", WeChatConfig.APPID);
		HttpGet httpGet = new HttpGet(requestURL);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(httpGet);
		String entityContent = EntityUtils.toString(response.getEntity());

		return JSON.parseObject(entityContent, SnsUserinfo.class);
	}
}
