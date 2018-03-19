package com.cnfantasia.wl.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

import com.cnfantasia.wl.wechat.WeChatConstant;

/**
 * 模板消息发送器
 * 
 * @author wenfq
 * 
 */
public class TemplateMsgSender {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String postData = "{\"touser\":\"oedx3uInJvB8bAzfQeullVAerrr4\",\"template_id\":\"hDCYRbZmGazj46IR_wVyPLS1MahXcz2yw_FwlGQciyA\",\"url\":\"http://weixin.qq.com/download\",\"topcolor\":\"#FF0000\",\"data\":{\"first\":{\"value\":\"恭喜你购买成功！我们已收到您的货款，开始为您打包商品，请耐心等待: )\",\"color\":\"#173177\"},\"orderMoneySum\":{\"value\":\"88.88元\",\"color\":\"#173177\"},\"orderProductName\":{\"value\":\"床上用品三件套\",\"color\":\"#173177\"},\"remark\":{\"value\":\"欢迎再次购买！\",\"color\":\"#173177\"}}}";
		sendMessage(postData);
	}

	/**
	 * 发送业务消息
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String sendMessage(String postData) throws ClientProtocolException, IOException {
		String sendURL = WeChatConstant.TEMPLATE_SEND_URL.replace("ACCESS_TOKEN", AccessTokenGetter.getAccess_token());
		URL urlPost = new URL(sendURL);
		HttpURLConnection httpURLConnection = (HttpURLConnection) urlPost.openConnection();

		httpURLConnection.setRequestMethod("POST");

		httpURLConnection.setDoOutput(true);// 是否输入参数
		byte[] bypes = postData.toString().getBytes();
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
}
