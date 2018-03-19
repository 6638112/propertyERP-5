/**   
* Filename:    TestPush.java   
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
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.common.utils.ComparatorNameValuePair;

/**
 * Filename:    TestPush.java
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
public class TestPush {
	public static void main(String[] args) throws Exception {
//		String postData = "http://127.0.0.1:8080/API/kitchen/qryCookbookTypes.json";
		String postUrl = "http://channel.api.duapp.com/rest/2.0/channel/channel";
		String secret_key = "O4xG1yAgyTZBIsZicAKfsiHAugXe4tUn";
		HttpPost httpPost = new HttpPost(postUrl);
		{//post param
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			formParams.add(new BasicNameValuePair("method", "push_msg"));
			formParams.add(new BasicNameValuePair("apikey", "cimduL99nuW9yTXch59s7w9G"));
//			formParams.add(new BasicNameValuePair("user_id", "618862212467140199"));//TEST
//			formParams.add(new BasicNameValuePair("user_id", "721324757303429927"));//TEST
//			formParams.add(new BasicNameValuePair("user_id", "636893123020752252"));//TEST
//			formParams.add(new BasicNameValuePair("user_id", "966893747997688896"));//TEST
			formParams.add(new BasicNameValuePair("user_id", "650505244677087403"));//TEST
			/**
			 * 推送类型，取值范围为：1～3 
			 * 1：单个人，必须指定user_id 和 channel_id （指定用户的指定设备）或者user_id（指定用户的所有设备）
			 * 2：一群人，必须指定 tag
			 * 3：所有人，无需指定tag、user_id、channel_id 
			 */
			formParams.add(new BasicNameValuePair("push_type", "1"));
//			formParams.add(new BasicNameValuePair("channel_id", "4562239449835996816"));//TEST
//			formParams.add(new BasicNameValuePair("channel_id", "4162770478015188108"));//TEST
//			formParams.add(new BasicNameValuePair("channel_id", "4162770478015188108"));//TEST
//			formParams.add(new BasicNameValuePair("channel_id", "3553723504512982397"));//TEST
			formParams.add(new BasicNameValuePair("channel_id", "3857208559430721857"));//TEST
//			formParams.add(new BasicNameValuePair("tag", ""));
			/**
			 * 设备类型，取值范围为：1～5 
			 * 1：浏览器设备；
			 * 2：PC设备；
			 * 3：Andriod设备；
			 * 4：iOS设备；
			 * 5：Windows Phone设备； 
			 */
			formParams.add(new BasicNameValuePair("device_type", "3"));
			/**
			 * 消息类型
			 * 0：消息（透传给应用的消息体）
			 * 1：通知（对应设备上的消息通知）默认值为0。 
			 */
			formParams.add(new BasicNameValuePair("message_type", "1"));
//			formParams.add(new BasicNameValuePair("messages", "{\"title\":\"\",\"description\":\"test\"}"));
//			"messageContent":"{\"description\":\"\\r\\n亲爱的业主们：\\r\\n  小长假归来是否已调整过来了呢？大家在回归正常生活作息的同时，也要记得注意水、电、煤的"
//					+ "安全。\\r\\n  在此，祝业主们生活开心，工作顺心！\",\"open_type\":\"2\",\"pkg_content\":\"intent:#Intent;launc"
//					+ "hFlags=0x10000000;component=com.jiefangqu.living/.act.property.AnnouncementDetailsAct;S.id=100018;end\",\"titl"
//					+ "e\":\"长假归来温馨提示\"}","messageId":100018,"messageType":3,"message_type":"1","msg_keys":"50083_100018",""
//							+ "push_type":"1","userHasMessageId":70128,"userIdType":{"userId":50036,"userType":1}}

//			formParams.add(new BasicNameValuePair("messages", "{\"title\":\"解放区\",\"description\":\"您有门牌通过验证\",\"pkg_content\":\"intent:#Intent;launchFlags=0x10000000;component=com.jiefangqu.living/.act.ChangePlaceAct;end\",\"open_type\":\"2\"}"));
			{
				Map<String,Object> messageMap = new HashMap<String, Object>();
				messageMap.put("title", "测试消息推送");
				messageMap.put("description", "测试信息描述");
//				messageMap.put("pkg_content", "intent:#Intent;launchFlags=0x10000000;component=com.jiefangqu.living/.act.huan.ExchangeDetailAct;S.id=100103;i.id=50155;end");
//				messageMap.put("pkg_content", "intent:#Intent;launchFlags=0x10000000;component=com.jiefangqu.living/.act.mine.MyResidentsAct;S.id=50002;end");
//				messageMap.put("pkg_content", "intent:#Intent;launchFlags=0x10000000;component=com.jiefangqu.living/.act.mine.MyGroupBuildingAct;end");
//				messageMap.put("pkg_content", "intent:#Intent;launchFlags=0x10000000;component=com.jiefangqu.living/.act.property.CheckDoorplateDetailAct;S.roomId=50002;end");
//				messageMap.put("pkg_content", "intent:#Intent;launchFlags=0x10000000;component=com.jiefangqu.living/.act.MyDarwListAct;i.month=8;i.year=2014;end");
				messageMap.put("pkg_content", "");
				messageMap.put("open_type", "2");
				formParams.add(new BasicNameValuePair("messages",JSON.toJSONString(messageMap)));

//				String messages = "{\"description\":\"亲爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？爱的业主们：小长假归来是否已调整过来了呢？大家在回归正常生活作息的同时，也要记得注意水、电、煤的"
//						+ "安全。\\r\\n  在此，	祝业 主们生活开心，工作顺心！\",\"open_type\":\"2\",\"pkg_content\":\"intent:#Intent;launc"
//						+ "hFlags=0x10000000;component=com.jiefangqu.living/.act.property.AnnouncementDetailsAct;S.id=100018;end\",\"titl"
//						+ "e\":\"长假归来温馨提示\"}";
//				messages = messages.replace("\\r", "");
//				messages = messages.replace("\\n", "");
//				messages = messages.replace("\t", "");
//				formParams.add(new BasicNameValuePair("messages",messages));

			}
			
//			formParams.add(new BasicNameValuePair("msg_keys", "testkey"));
			formParams.add(new BasicNameValuePair("msg_keys", "51528_100103"));
//			formParams.add(new BasicNameValuePair("message_expires", ""));
//			formParams.add(new BasicNameValuePair("deploy_status", ""));
			formParams.add(new BasicNameValuePair("timestamp", "51528_100103"));
			formParams.add(new BasicNameValuePair("expires", "1313293565"));
//			formParams.add(new BasicNameValuePair("v", "2"));
			{
				String sign = genSign(secret_key, "POST", postUrl, formParams);
				formParams.add(new BasicNameValuePair("sign", sign));
			}
			
			HttpEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
			httpPost.setEntity(entity);
		}
//		{//ok
			httpPost.addHeader("deviceId", "aaaa");
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
