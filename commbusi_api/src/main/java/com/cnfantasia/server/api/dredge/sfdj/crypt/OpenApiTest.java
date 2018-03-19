package com.cnfantasia.server.api.dredge.sfdj.crypt;

import java.util.HashMap;
import java.util.Map;

/**
 * 开放API单元测试
 * 
 * @author fengli
 * @date 2015年6月1日 下午4:50:25
 */
public class OpenApiTest {

	public void testUserInfo() {
	    try {
	        String appkey = "753a55bc26583c528b964d45d7305b28bafe7c75";
            String secret = "3b47a59e834bf11564f899586665a310";
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("app_key", appkey);
            params.put("open_token", "cf6b965c76684eaaa131339b838ae211");
            params.put("timestamp", String.valueOf("123456"));
            System.out.println("加密前：" + OpenApiParameterParser.paramsToString(params, "utf-8"));
            Map newParams = OpenApiParameterParser.encodeParametes(params, secret);
            String urlparams = OpenApiParameterParser.paramsToString(newParams, "utf-8");
            System.out.println("****** url params : " + urlparams);
            String resp = HttpUtils.getJson("http://open.putao.so/v1/api/user/info?" + urlparams, new HashMap());
            System.out.println(resp);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}   
}
