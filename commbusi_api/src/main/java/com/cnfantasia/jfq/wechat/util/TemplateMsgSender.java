package com.cnfantasia.jfq.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.HttpException;
import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.HttpUtil;

/**
 * 模板消息发送器
 * 
 * @author wenfq
 * 
 */
public class TemplateMsgSender {

	/**
	 * 发送模板消息
	 */
	private static final String TEMPLATE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	public static void main(String[] args) throws ClientProtocolException, IOException {
//		String postData = "{\"touser\":\"oedx3uInJvB8bAzfQeullVAerrr4\",\"template_id\":\"hDCYRbZmGazj46IR_wVyPLS1MahXcz2yw_FwlGQciyA\",\"url\":\"http://weixin.qq.com/download\",\"topcolor\":\"#FF0000\",\"data\":{\"first\":{\"value\":\"恭喜你购买成功！我们已收到您的货款，开始为您打包商品，请耐心等待: )\",\"color\":\"#173177\"},\"orderMoneySum\":{\"value\":\"88.88元\",\"color\":\"#173177\"},\"orderProductName\":{\"value\":\"床上用品三件套\",\"color\":\"#173177\"},\"remark\":{\"value\":\"欢迎再次购买！\",\"color\":\"#173177\"}}}";
//		sendMessage(postData);
		getAccess_token();
	}

	/**
	 * 发送业务消息
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String sendMessage(String postData) throws ClientProtocolException, IOException {
		String sendURL = TEMPLATE_SEND_URL.replace("ACCESS_TOKEN", getAccess_token());
		URL urlPost = new URL(sendURL);
		HttpURLConnection httpURLConnection = (HttpURLConnection) urlPost.openConnection();

		httpURLConnection.setRequestMethod("POST");

		httpURLConnection.setDoOutput(true);// 是否输入参数
		byte[] bypes = postData.getBytes("utf-8");
		httpURLConnection.getOutputStream().write(bypes);// 输入参数

		System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
		System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒 

		httpURLConnection.connect();
		InputStream is = httpURLConnection.getInputStream();
		int size = is.available();
		byte[] jsonBytes = new byte[size];
		is.read(jsonBytes);
		String jsonMessage = new String(jsonBytes, "UTF-8");
		System.out.println(jsonMessage);
		return jsonMessage;
	}

	private static String getAccess_token() throws HttpException, IOException {
		ISysParamManager sysParamManager = (ISysParamManager) CnfantasiaCommbusiUtil.getBeanManager("sysParamManager");
		String GET_ACCESS_TOKEN_URL = sysParamManager.getSysParaValue("GET_ACCESS_TOKEN_URL");
		String returnV = new HttpUtil().get(GET_ACCESS_TOKEN_URL);
		System.out.println("the access token is: " + returnV);
		return JSONObject.parseObject(returnV).getString("AccessToken");
	}
}
