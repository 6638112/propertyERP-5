package com.tenpay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnfantasia.server.api.payment.constant.WeiXinPayConstantUtil.WeiXinPay_GoalAccType;
import com.tenpay.client.TenpayHttpClient;
import com.tenpay.util.ConstantUtil;
import com.tenpay.util.JsonUtil;
import com.tenpay.util.WXUtil;

public class AccessTokenRequestHandler extends RequestHandler {
	public AccessTokenRequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}

	private static String access_token = "";

	/**
	 * 获取凭证access_token
	 * @return
	 */
	public static String getAccessToken(WeiXinPay_GoalAccType goalAccType) {
		if ("".equals(access_token)) {// 如果为空直接获取
			return getTokenReal(goalAccType);
		}

		if (tokenIsExpire(goalAccType,access_token)) {// 如果过期重新获取
			return getTokenReal(goalAccType);
		}
		return access_token;
	}

	/**
	 * 实际获取access_token的方法
	 * @return
	 */
	protected static String getTokenReal(WeiXinPay_GoalAccType goalAccType) {
		String requestUrl = ConstantUtil.TOKENURL + "?grant_type=" + ConstantUtil.GRANT_TYPE + "&appid="
				+ ConstantUtil.getAPP_ID(goalAccType) + "&secret=" + ConstantUtil.getAPP_SECRET(goalAccType);
		String resContent = "";
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setMethod("GET");
		httpClient.setReqContent(requestUrl);
		if (httpClient.call()) {
			resContent = httpClient.getResContent();
			if (resContent.indexOf(ConstantUtil.ACCESS_TOKEN) > 0) {
				access_token = JsonUtil.getJsonValue(resContent, ConstantUtil.ACCESS_TOKEN);
			} else {
				System.out.println("获取access_token值返回错误！！！");
			}
		} else {
			System.out.println("后台调用通信失败");
			System.out.println(httpClient.getResponseCode());
			System.out.println(httpClient.getErrInfo());
			// 有可能因为网络原因，请求已经处理，但未收到应答。
		}

		return access_token;
	}

	/**
	 * 判断传递过来的参数access_token是否过期
	 * @param access_token
	 * @return
	 */
	private static boolean tokenIsExpire(WeiXinPay_GoalAccType goalAccType,String access_token) {
		boolean flag = false;
		PrepayIdRequestHandler wxReqHandler = new PrepayIdRequestHandler(null, null);
		wxReqHandler.setParameter("appid", ConstantUtil.getAPP_ID(goalAccType));
		wxReqHandler.setParameter("appkey",ConstantUtil.getAPP_KEY(goalAccType));
		wxReqHandler.setParameter("noncestr", WXUtil.getNonceStr());
//		wxReqHandler.setParameter("package", ConstantUtil.packageValue);//syl--del
		wxReqHandler.setParameter("timestamp", WXUtil.getTimeStamp());
//		wxReqHandler.setParameter("traceid", ConstantUtil.traceid);//syl--del

		// 生成支付签名
		String sign = wxReqHandler.createSHA1Sign();
		wxReqHandler.setParameter("app_signature", sign);
		wxReqHandler.setParameter("sign_method", ConstantUtil.SIGN_METHOD);
		String gateUrl = ConstantUtil.GATEURL + access_token;
		wxReqHandler.setGateUrl(gateUrl);

		// 发送请求
		String accesstoken = wxReqHandler.sendAccessToken();
		if (ConstantUtil.EXPIRE_ERRCODE.equals(accesstoken) || ConstantUtil.FAIL_ERRCODE.equals(accesstoken))
			flag = true;
		return flag;
	}

}
