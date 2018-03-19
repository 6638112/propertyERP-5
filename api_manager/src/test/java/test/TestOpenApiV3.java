package test;

import java.util.HashMap;

import com.qq.open.OpenApiV3;
import com.qq.open.OpensnsException;

/**
 * OpenAPI V3 SDK 示例代码
 * 
 * @version 3.0.0
 * @since jdk1.5
 * @author open.qq.com
 * @copyright © 2012, Tencent Corporation. All rights reserved.
 * @History: 3.0.0 | nemozhang | 2012-03-21 12:01:05 | initialization
 * 
 */

public class TestOpenApiV3 {
//	public static void main(String args[]) {
//		// 应用基本信息
//		String appid = "1101365148";
//		String appkey = "O1RMqUGx9IkHXSyQ";
//		// 用户的OpenID/OpenKey
//		String openid = "52B8CCD45B1BEC1EF4B9CED17BCD3DFC";
//		String openkey = "100330589";
//
////		oauth_consumer_key=100330589&access_token=B75E10024024C6310CE1A203238E5156&openid=52B8CCD45B1BEC1EF4B9CED17BCD3DFC&format=json
//		// OpenAPI的服务器IP
//		// 最新的API服务器地址请参考wiki文档:
//		// http://wiki.open.qq.com/wiki/API3.0%E6%96%87%E6%A1%A3
//		String serverName = "119.147.19.43";
//
//		// 所要访问的平台, pf的其他取值参考wiki文档:
//		// http://wiki.open.qq.com/wiki/API3.0%E6%96%87%E6%A1%A3
//		String pf = "qzone";
//
//		OpenApiV3 sdk = new OpenApiV3(appid, appkey);
//		sdk.setServerName(serverName);
//
//		System.out.println("===========test GetUserInfo===========");
//		testGetUserInfo(sdk, openid, openkey, pf);
//	}

	/**
	 * 测试调用UserInfo接口
	 * 
	 */
	public static void testGetUserInfo(OpenApiV3 sdk, String openid, String openkey, String pf) {
		// 指定OpenApi Cgi名字
		String scriptName = "/v3/user/get_info";

		// 指定HTTP请求协议类型
		String protocol = "http";

		// 填充URL请求参数
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("openid", openid);
		params.put("openkey", openkey);
		params.put("pf", pf);

		try {
			String resp = sdk.api(scriptName, params, protocol);
			System.out.println(resp);
		} catch (OpensnsException e) {
			System.out.printf("Request Failed. code:%d, msg:%s\n", e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws OpensnsException {
		String serverName = "119.147.19.43";
//		String serverName = "openapi.tencentyun.com";
		String appid = "1101365148";
		String appkey = "O1RMqUGx9IkHXSyQ";
		// 指定OpenApi Cgi名字
		String scriptName = "/v3/user/get_info";
		// 指定HTTP请求协议类型
		String protocol = "http";
//		oauth_consumer_key=100330589&access_token=B75E10024024C6310CE1A203238E5156&openid=52B8CCD45B1BEC1EF4B9CED17BCD3DFC&format=json
		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("oauth_consumer_key", "100330589");
		params.put("access_token", "B75E10024024C6310CE1A203238E5156");
		params.put("openid", "52B8CCD45B1BEC1EF4B9CED17BCD3DFC");
//		params.put("openkey", "100330589");
		params.put("format", "json");
		params.put("pf", "qzone");
		OpenApiV3 sdk = new OpenApiV3(appid, appkey);
		sdk.setServerName(serverName);
		String resp = sdk.api(scriptName, params, protocol);
		System.out.println(resp);
	}
}
