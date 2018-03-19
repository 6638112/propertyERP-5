/**   
* Filename:    HjxPushUtil.java   
* @version:    1.0  
* Create at:   2015年8月4日 下午12:55:02   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月4日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuyorder.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.business.pub.utils.Md5Util;

/**
 * Filename:    HjxPushUtil.java
 * @version:    1.0.0
 * Create at:   2015年8月4日 下午12:55:02
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月4日       shiyl             1.0             1.0 Version
 */
public class HjxPushUtil {
	private static Log logger = LogFactory.getLog(HjxPushUtil.class);
	
	/**
	 * 推送订单到海吉星
	 * 
	 * @param orderInfoJson
	 *            海吉星所需的订单信息
	 * @return responseString 对方服务器返回的结果串
	 */
	public static String pushOrder2HJX(String orderInfoJson,String HJX_Source_tag,String HJX_Version,String HJX_Salt,String OrderPushURL) {
		CloseableHttpClient client = HttpClients.createDefault();

		// 创建参数队列 
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair(SysParamKey.HJX_Source_tag, HJX_Source_tag));
		formparams.add(new BasicNameValuePair(SysParamKey.HJX_Version, HJX_Version));
		formparams.add(new BasicNameValuePair("order", orderInfoJson));

		StringBuilder paramString = new StringBuilder();
		paramString.append(SysParamKey.HJX_Source_tag + "=" + HJX_Source_tag + "|");
		paramString.append(SysParamKey.HJX_Version + "=" + HJX_Version + "|");
		paramString.append("order=" + orderInfoJson + "|");
		paramString.append(HJX_Salt);
		logger.info("push params is: " + paramString.toString());
		String signature = Md5Util.MD5(paramString.toString()); //签名
		logger.info("signature is: " + signature);
		formparams.add(new BasicNameValuePair("signature", signature));
		logger.info("Charset.defaultCharset().name(): " + Charset.defaultCharset().name());

		//String unifiedorderURL = "http://119.136.31.227:86/api/3rd/place_order_v1.php";
		//String unifiedorderURL = "http://www.hjxmall.com/api/3rd/place_order_v1.php";
		HttpPost httpPost = new HttpPost(OrderPushURL);

		HttpEntity entity;
		String responseString = null;
		CloseableHttpResponse hcResponse = null;
		try {
			entity = new UrlEncodedFormEntity(formparams, HTTP.UTF_8);
			httpPost.setEntity(entity);
			hcResponse = client.execute(httpPost);
			HttpEntity responseEntity = hcResponse.getEntity();
			responseString = EntityUtils.toString(responseEntity, HTTP.UTF_8);
			logger.info("response from hjx is: " + responseString);
			//{"code":"0","order_tag":"100058180","sub_order_tags":["1501271146560000091202"], "description":"成功！"}
			//{"code":"-1","description":"重复订单！"}
			
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseString = "{\"code\":\"-1\",\"description\":\"推送过程有异常，订单推送失败\"}";
		} finally {
			try {
				client.close();
				hcResponse.close();
			} catch (IOException e) {
			}
		}
		return responseString;
	}
	
}
