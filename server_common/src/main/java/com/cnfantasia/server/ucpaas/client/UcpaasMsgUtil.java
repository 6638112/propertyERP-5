package com.cnfantasia.server.ucpaas.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.cnfantasia.server.ucpaas.SysConfig;

public class UcpaasMsgUtil {
	
	private static Log logger = LogFactory.getLog(UcpaasMsgUtil.class);
	
	/**
	 * 用云之讯接口发送短信
	 * @param phone 手机号码
	 * @param shortMsg 短信内容
	 * @return
	 */
	public static boolean sendMsg(String phone, String shortMsg) {
		String result = "";
		try {
			String accountSid = SysConfig.getInstance().getProperty("accountSid");
			String token = SysConfig.getInstance().getProperty("token");
			String appId = SysConfig.getInstance().getProperty("appId");
			String templateId = SysConfig.getInstance().getProperty("templateId");
			
			AbsRestClient client = new JsonReqClient();
			result = client.templateSMS(accountSid, token, appId, templateId, phone, shortMsg);
			logger.debug("ucpaas-sendMsg:" + result);
			//成功后返回  {"resp":{"respCode":"000000","templateSMS":{"createDate":"20160419165555","smsId":"9b2d2dbfa845ba1e4d5ecf29f89cff79"}}}
			
			JSONObject resultJson = new JSONObject(result);
			
			String respCode = resultJson.getJSONObject("resultJson").getString("respCode");
			if("000000".equals(respCode)) {
				return true;
			} else {
				return false;
			}
		}  catch (JSONException e) {
			logger.error("result-error:" + result);
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		UcpaasMsgUtil.sendMsg("13723460250", "您有新的订单了，请到APP查看订单信息，并尽快联系业主进行上门维修！");
	}

}
