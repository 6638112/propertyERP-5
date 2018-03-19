/**   
* Filename:    TestPushOffline.java   
* @version:    1.0  
* Create at:   2015年10月21日 下午4:06:37   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年10月21日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.msgpush;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.msgpush.constant.BaiduPushKey;
import com.cnfantasia.server.api.msgpush.util.BaidupushUtil;

/**
 * Filename:    TestPushOffline.java
 * @version:    1.0.0
 * Create at:   2015年10月21日 下午4:06:37
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年10月21日       shiyl             1.0             1.0 Version
 */
public class TestPushOffline {
	public static void main(String[] args) {
		String title = "批量物业宝title";
		String content = "批量物业宝内容";
		push(title, content, new PushConfig(2, "725399635682359694", "5280789176935557029"));
	}
	public static String push(String title,String content,PushConfig config){
			String device_type = config.getDeviceType();
			String res =  doPush(title,content, config.getBaiduChannelId(), config.getBaiduUserId(), device_type);
			System.out.println("device_type:"+device_type+",BaiduUserId:"+config.getBaiduUserId()+",BaiduChannelId:"+config.getBaiduChannelId());
			System.out.println(res);
			return res;
	}
	public static void push(String title,String content,Set<PushConfig> pushConfigList){
//		int size = pushConfigList.size();
		for(PushConfig config:pushConfigList){
			push(title, content, config);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
//			size--;
//			System.out.println(size);
		}
	}
	public static String doPush(String title,String content,String baiduChannelId,String baiduUserId,String device_type){
		String messageContent = device_type.equals("4")?generateIosContent(title, content):generateAndroidContent(title, content);
		messageContent = messageContent.replace("\\r", "");
		messageContent = messageContent.replace("\\n", "");
		messageContent = messageContent.replace("\t", "");
		String msg_keys = new Date().getTime()+"";//TODO 临时设置
		/**
		 * 部署状态。指定应用当前的部署状态，可取值：
		 * 1：开发状态
		 * 2：生产状态
		 * 若不指定，则默认设置为生产状态。特别提醒：该功能只支持ios设备类型。 
		 */
		String deploy_status = "2";
		String apiUrl = BatchPushUtil.apiUrl;
		String apikey = BatchPushUtil.apikey;
		String secret_key = BatchPushUtil.secret_key;
		
		Long timestamp = new Date().getTime();
		
		/**
		 * 推送类型，取值范围为：1～3 
		 * 1：单个人，必须指定user_id 和 channel_id （指定用户的指定设备）或者user_id（指定用户的所有设备）
		 * 2：一群人，必须指定 tag
		 * 3：所有人，无需指定tag、user_id、channel_id 
		 */
		String push_type = "1";
		/**
		 * 消息类型
		 * 0：消息（透传给应用的消息体）
		 * 1：通知（对应设备上的消息通知）默认值为0。 
		 */
		String message_type = "1";
		String tag = null;
	
		String res = null;
		try {
			Long expires = 1313293565L;
			res = BaidupushUtil.pushMessage(apiUrl, apikey, secret_key, msg_keys, messageContent, baiduChannelId,
					baiduUserId, push_type, device_type, message_type, tag, timestamp, expires, deploy_status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static String generateIosContent(String title,String content){
//		title = "ceshTitle2015-1-15aa";
//		content = "ceshContent2015-1-15aa";
		Map<String,Object> messageMap = new HashMap<String, Object>();
//			String targetView = "discountReveice";
		{
			Map<String,Object> apsMap = new HashMap<String, Object>();
			apsMap.put(BaiduPushKey.Content_IOS_aps_alert, content);
			apsMap.put(BaiduPushKey.Content_IOS_aps_sound, "");
			apsMap.put(BaiduPushKey.Content_IOS_aps_badge, "");
			messageMap.put(BaiduPushKey.Content_IOS_aps, apsMap);
		}
//		messageMap.put(BaiduPushKey.Content_IOS_msgType, "3");//使用targetView标识消息类型
		messageMap.put(BaiduPushKey.Content_IOS_detailId, 100217);
		messageMap.put(BaiduPushKey.Content_IOS_title, title);
		return JSON.toJSONString(messageMap);
	}
//	public static String generateAndroidContent(String title,String content){
//		Map<String,Object> messageMap = new HashMap<String, Object>();
//		messageMap.put("title", title);
//		messageMap.put("description", content);
//		messageMap.put("notification_builder_id", 0);
//		messageMap.put("notification_basic_style", 4);
//		messageMap.put("pkg_content", "");
//		messageMap.put("open_type", "2");
//		return JSON.toJSONString(messageMap);
//	}
	public static String generateAndroidContent(String title,String content){
		Map<String,Object> messageMap = new HashMap<String, Object>();
		messageMap.put("title", title);
		messageMap.put("description", content);
		messageMap.put("pkg_content", "");
		messageMap.put("open_type", "2");
//		messageMap.put("pkg_content", "intent:#Intent;launchFlags=0x10000000;component=com.jiefangqu.living/.act.huan.ExchangeDetailAct;S.id=100103;i.id=50155;end");
//		messageMap.put("pkg_content", "intent:#Intent;launchFlags=0x10000000;component=com.jiefangqu.living/.act.main.SquareAct;end");
		return JSON.toJSONString(messageMap);
	}
	
}
