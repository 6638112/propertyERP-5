/**   
* Filename:    BaidupushUtil.java   
* @version:    1.0  
* Create at:   2014年9月22日 上午3:14:32   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.msgpush.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.common.utils.ComparatorNameValuePair;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    BaidupushUtil.java
 * @version:    1.0.0
 * Create at:   2014年9月22日 上午3:14:32
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月22日       shiyl             1.0             1.0 Version
 */
public class BaidupushUtil {
	private static Log logger = LogFactory.getLog(BaidupushUtil.class);
//	public static void main(String[] args) {
//		System.out.println(RelativeDateFormat.format(System.currentTimeMillis()-1313293563*1000L));
//	}
	
	/**
	* $secret_key //应用的secret key
	* $method //GET或POST
	* $url url
	* $arrContent //请求参数(包括GET和POST的所有参数，不含计算的sign)
	* return $sign string
	 * @throws UnsupportedEncodingException 
	**/
	public static String genSign(String secret_key, String method, String url,List<NameValuePair> formParams) throws UnsupportedEncodingException {
	    StringBuffer gatherSbf = new StringBuffer();
	    gatherSbf.append(method);
	    gatherSbf.append(url);
	    Collections.sort(formParams, new ComparatorNameValuePair());
	    for(NameValuePair tmpNameValuePair:formParams){
	    	gatherSbf.append(tmpNameValuePair.getName());
	    	gatherSbf.append("=");
	    	gatherSbf.append(tmpNameValuePair.getValue());
	    }
	    gatherSbf.append(secret_key);
	    String resSign = Md5Util.MD5(URLEncoder.encode(gatherSbf.toString(),"UTF-8"));
	    return resSign;
	}
	
	public static String pushMessage(
			String apiUrl,String apikey,String secret_key
			,String msg_keys,String messagesContent, String baiduChannelId, String baiduUserId, String push_type
			,String device_type, String message_type, String tag
			,Long timestamp,Long expires,String deploy_status) throws UnsupportedEncodingException, IOException {
		logger.debug("BaidupushUtil.pushMessage:" + apiUrl + "," + apikey + "," + secret_key + "," + msg_keys + "," + messagesContent + "," + "baiduChannelId" + "," + baiduUserId
				 + "," + push_type + "," + device_type + "," + message_type + "," + tag + "," + timestamp + "," + expires + "," + deploy_status);
		
		
		HttpPost httpPost = new HttpPost(apiUrl);
		{// post param
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			formParams.add(new BasicNameValuePair("method", "push_msg"));
			formParams.add(new BasicNameValuePair("apikey", apikey));

			/**
			 * 推送类型，取值范围为：1～3 1：单个人，必须指定user_id 和 channel_id
			 * （指定用户的指定设备）或者user_id（指定用户的所有设备） 2：一群人，必须指定 tag
			 * 3：所有人，无需指定tag、user_id、channel_id
			 */
			formParams.add(new BasicNameValuePair("push_type", push_type));
			if (!StringUtils.isEmpty(baiduChannelId)) {
				formParams.add(new BasicNameValuePair("channel_id", baiduChannelId));
			}
			if (!StringUtils.isEmpty(baiduUserId)) {
				formParams.add(new BasicNameValuePair("user_id", baiduUserId));
			}
			if (!StringUtils.isEmpty(tag)) {
				formParams.add(new BasicNameValuePair("tag", tag));
			}
			/**
			 * 设备类型，取值范围为：1～5 1：浏览器设备； 2：PC设备； 3：Andriod设备； 4：iOS设备； 5：Windows
			 * Phone设备；
			 */
			formParams.add(new BasicNameValuePair("device_type", device_type));
			/**
			 * 消息类型 0：消息（透传给应用的消息体） 1：通知（对应设备上的消息通知）默认值为0。
			 */
			formParams.add(new BasicNameValuePair("message_type", message_type));
			{// 组装消息内容
				formParams.add(new BasicNameValuePair("messages", messagesContent));
			}
			formParams.add(new BasicNameValuePair("msg_keys", msg_keys));
			// formParams.add(new BasicNameValuePair("message_expires", ""));
			if(!StringUtils.isEmpty(deploy_status)){
				formParams.add(new BasicNameValuePair("deploy_status",deploy_status));
			}
			formParams.add(new BasicNameValuePair("timestamp", timestamp.toString()));//用户发起请求时的unix时间戳。本次请求签名的有效时间为该时间戳+10分钟。
			if(expires!=null){
				formParams.add(new BasicNameValuePair("expires", expires.toString()));//用户指定本次请求签名的失效时间。格式为unix时间戳形式。 
			}
			// formParams.add(new BasicNameValuePair("v", "2"));
			{
				String sign = BaidupushUtil.genSign(secret_key, "POST", apiUrl, formParams);
				formParams.add(new BasicNameValuePair("sign", sign));
			}
			logger.debug("The baidu push formParams is :"+JSON.toJSONString(formParams));
			HttpEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
			httpPost.setEntity(entity);
		}
		// {//ok
		// httpPost.addHeader("deviceId", "aaaa");
		// }
		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse response = client.execute(httpPost);
		String res = null;
		{// response.getEntity()
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				logger.debug("The baidu push response status is :"+response.getStatusLine().getStatusCode());
				res = new String(EntityUtils.toByteArray(entity), "UTF-8");
				logger.debug("The baidu push response data is :"+res);
			}
		}
		return res;
	}
	
}
