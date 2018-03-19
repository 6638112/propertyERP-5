/**   
* Filename:    TestPushIos.java   
* @version:    1.0  
* Create at:   2014年9月19日 上午7:35:16   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月19日    shiyl      1.0         1.0 Version   
*/
package test.baiduPush;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cnfantasia.server.api.msgpush.constant.BaiduPushKey;
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.common.utils.ComparatorNameValuePair;

/**
 * Filename:    TestPushIos.java
 * @version:    1.0.0
 * Create at:   2014年9月19日 上午7:35:16
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月19日       shiyl             1.0             1.0 Version
 */
/**
 * {"request_id":3041607951,"response_params":{"success_amount":1,"msgids":["1889223146862124635"]}} 成功返回
 */
public class TestPushIos {
	public static void main(String[] args) throws Exception {
//		String postData = "http://127.0.0.1:8080/API/kitchen/qryCookbookTypes.json";
		String postUrl = "http://channel.api.duapp.com/rest/2.0/channel/channel";
		String secret_key = "O4xG1yAgyTZBIsZicAKfsiHAugXe4tUn";
//		String secret_key = "mQfG4Q7nFkw6eoGmbUKHAIL2L5uUPnGn";
		HttpPost httpPost = new HttpPost(postUrl);
		{//post param
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			formParams.add(new BasicNameValuePair("apikey", "cimduL99nuW9yTXch59s7w9G"));
//			formParams.add(new BasicNameValuePair("apikey", "zQUsTCuGEnM57xKMOVeHNzXW"));
			formParams.add(new BasicNameValuePair("method", "push_msg"));
//			formParams.add(new BasicNameValuePair("user_id", "924140467609122745"));//TEST
//			formParams.add(new BasicNameValuePair("user_id", "585404724101436381"));//TEST
//			formParams.add(new BasicNameValuePair("user_id", "585404724101436381"));//TEST
//			formParams.add(new BasicNameValuePair("user_id", "924140467609122745"));//TEST
//			formParams.add(new BasicNameValuePair("user_id", "599747606911394761"));//TEST
			formParams.add(new BasicNameValuePair("user_id", "924140467609122745"));//TEST
			formParams.add(new BasicNameValuePair("user_id", "924140467609122745"));//TEST
			/**
			 * 推送类型，取值范围为：1～3 
			 * 1：单个人，必须指定user_id 和 channel_id （指定用户的指定设备）或者user_id（指定用户的所有设备）
			 * 2：一群人，必须指定 tag
			 * 3：所有人，无需指定tag、user_id、channel_id 
			 */
			formParams.add(new BasicNameValuePair("push_type", "1"));
//			formParams.add(new BasicNameValuePair("channel_id", "5437005120865503622"));//TEST
//			formParams.add(new BasicNameValuePair("channel_id", "5633060709666062085"));//TEST
//			formParams.add(new BasicNameValuePair("channel_id", "5633060709666062085"));//TEST
//			formParams.add(new BasicNameValuePair("channel_id", "5437005120865503622"));//TEST
//			formParams.add(new BasicNameValuePair("channel_id", "4808241417646427169"));//TEST
//			formParams.add(new BasicNameValuePair("channel_id", "5437005120865503622"));//TEST
			formParams.add(new BasicNameValuePair("channel_id", "5437005120865503622"));//TEST
//			formParams.add(new BasicNameValuePair("tag", ""));
			/**
			 * 设备类型，取值范围为：1～5 
			 * 1：浏览器设备；
			 * 2：PC设备；
			 * 3：Andriod设备；
			 * 4：iOS设备；
			 * 5：Windows Phone设备； 
			 */
			formParams.add(new BasicNameValuePair("device_type", "4"));
			/**
			 * 消息类型
			 * 0：消息（透传给应用的消息体）
			 * 1：通知（对应设备上的消息通知）默认值为0。 
			 */
			formParams.add(new BasicNameValuePair("message_type", "1"));
//			{
//				Map<String,Object> messageMap = new HashMap<String, Object>();
//				
////				messageMap.put("pkg_content", "intent:#Intent;launchFlags=0x10000000;component=com.jiefangqu.living/.act.ChangePlaceAct;end");
////				messageMap.put("open_type", "2");
//				Map<String,Object> apsMap = new HashMap<String, Object>();
//				apsMap.put("alert", "alert123");
//				apsMap.put("sound", "");
//				apsMap.put("badge", "");
//				messageMap.put("aps", apsMap);
//				messageMap.put("msgType", "msgType1");
//				messageMap.put("title", "解放区22");
//				messageMap.put("description", "您有门牌通过验证");
//				formParams.add(new BasicNameValuePair("messages",JSON.toJSONString(messageMap)));
//			}
			{
				Map<String,Object> messageMap = new HashMap<String, Object>();
//				String targetView = "discountReveice";
				{
					Map<String,Object> apsMap = new HashMap<String, Object>();
					apsMap.put(BaiduPushKey.Content_IOS_aps_alert, "ceshContent2015-1-15aa");
					apsMap.put(BaiduPushKey.Content_IOS_aps_sound, "");
					apsMap.put(BaiduPushKey.Content_IOS_aps_badge, "");
//					{//keyValues
//						Map<String,Object> keyValues = null;
//							keyValues = new HashMap<String, Object>();
//							keyValues.put("month", "08");
//							keyValues.put("year", "2014");
//							messageMap.put(BaiduPushKey.Content_IOS_keyValues,keyValues);
//					}
					messageMap.put(BaiduPushKey.Content_IOS_aps, apsMap);
				}
				messageMap.put(BaiduPushKey.Content_IOS_msgType, "3");//使用targetView标识消息类型
				messageMap.put(BaiduPushKey.Content_IOS_detailId, 100217);
				messageMap.put(BaiduPushKey.Content_IOS_title, "ceshTitle2015-1-15aa");
				formParams.add(new BasicNameValuePair("messages",JSON.toJSONString(messageMap)));
			} 
			formParams.add(new BasicNameValuePair("msg_keys", "tes50169_100217tkey"));
//			formParams.add(new BasicNameValuePair("message_expires", ""));
			/**
			 * 部署状态。指定应用当前的部署状态，可取值：
			 * 1：开发状态
			 * 2：生产状态
			 * 若不指定，则默认设置为生产状态。特别提醒：该功能只支持ios设备类型。 
			 */
			formParams.add(new BasicNameValuePair("timestamp", "1421316642"));
			formParams.add(new BasicNameValuePair("deploy_status", "1"));
//			formParams.add(new BasicNameValuePair("expires", "1313293565"));
//			formParams.add(new BasicNameValuePair("v", "2"));
			{
				String sign = genSign(secret_key, "POST", postUrl, formParams);
				formParams.add(new BasicNameValuePair("sign", sign));
			}
			HttpEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
			httpPost.setEntity(entity);
		}
//		{//ok
//			httpPost.addHeader("deviceId", "aaaa");
//		}
		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse response = client.execute(httpPost);
		{// response.getEntity()
			System.out.println("显示返回数据:");
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				System.out.println("响应状态码:"+response.getStatusLine().getStatusCode());
				String res = new String(EntityUtils.toByteArray(entity), "UTF-8");
				System.out.println(res);
//				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//					String res = new String(EntityUtils.toByteArray(entity), "UTF-8");
//					System.out.println(res);
//				}
			}
		}
		
	}
	
	
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
	 
}
