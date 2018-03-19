/**   
* Filename:    BatchPushUtil.java   
* @version:    1.0  
* Create at:   2015年10月21日 下午5:02:33   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年10月21日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.msgpush;

import java.util.HashSet;
import java.util.Set;

/**
 * Filename:    BatchPushUtil.java
 * @version:    1.0.0
 * Create at:   2015年10月21日 下午5:02:33
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年10月21日       shiyl             1.0             1.0 Version
 */
public class BatchPushUtil {//3423353138679013483  
	public static Set<PushConfig> confList  = new HashSet<PushConfig>();
	public static String apiUrl = "http://channel.api.duapp.com/rest/2.0/channel/channel";
	//待修改的最新的
//	public static String apikey = "nfIPFeMEW1CLMirh4wkGNPk3";
//	public static String secret_key = "GPr9NYuGYSPjRVBfF9b6fshCyCdhVX8w";
	
		//当前线上的IOS ok  2
	public static String apikey = "cimduL99nuW9yTXch59s7w9G";
	public static String secret_key = "O4xG1yAgyTZBIsZicAKfsiHAugXe4tUn";
	
	//当前线上的Android ok  1
//	public static String apikey = "nfIPFeMEW1CLMirh4wkGNPk3";
//	public static String secret_key = "GPr9NYuGYSPjRVBfF9b6fshCyCdhVX8w";
	
	static{//使用百度demo绑定的，百度demo于当前线上的对应的ok
//		confList.add(new PushConfig(1, "641674361666369550", "4338290434617551290"));//syl
//		confList.add(new PushConfig(1, "832239959483466592", "3648429395095385089"));//sheng hua-baidu~demo
	}
	
	static{//新包配置
//		confList.add(new PushConfig(1, "1073445541450739694", "3514987713818623061"));//chen yue liang_del
//		confList.add(new PushConfig(1, "994375957603480296", "3514987713818623061"));//chen yue liang
	}
	
	static{//bak
//		confList.add(new PushConfig(2, "1130640887452010687", "4920366643834654858"));//lizhi
//		confList.add(new PushConfig(2, "725399635682359694", "5280789176935557029"));//weijian
//		confList.add(new PushConfig(1, "687045233074706433", "3941877722388971446"));//changchang
//		confList.add(new PushConfig(1, "641674361666369550", "4338290434617551290"));//syl
//		confList.add(new PushConfig(1, "1044410194527483404", "3553392816525401078"));//yukun
	}
	
	public static void main(String[] args) throws Exception {
//		String title = "物业缴费最后一天";
//		String content = "华海、湖北宝丰花园、公路局小区业主，上解放区缴1月物业费，15号为最后一天，抽到折扣还未缴费的的小主，抓紧上解放区缴费，过期不候哦。";
		
//		String title = "楼村花园各小主，物业缴费最后一天~";
//		String content = "楼村花园各小主，上解放区缴1月物业费，15号为最后一天，抽到折扣还未缴费的的小主，抓紧上解放区缴费，过期不候哦。";
//		
		String title = "1月物业费缴费提醒";
		String content = "上解放区抽1月物业费折扣啦，本月缴费截止日为2月24日，抽到折扣的业主可千万不要错过缴费时间哦，预祝业主/住户新春快乐。";
		
		
//		String content = "恭喜！解放君给大家送上“神器”啦！解放区APP专属理财神器上线！购买物业宝产品，免交全年物业费还能赚钱，快来体验吧！在解放区APP首页猛戳“物业宝”，马上专享尊贵服务。点击物业宝了解详情，或详询物业管理处。";
//		String content = "解放区APP专属理财神器上线！购买物业宝产品，免交全年物业费还能赚钱，快来体验吧！在解放区APP首页猛戳“物业宝”，马上专享尊贵服务。";
//		String content = "哦哦哦哦哦哦哦”！，。哦哦哦啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊";
//		String content = "解放区送神器啦！物业宝免物业费还能赚钱！详询管理处。";//ok
		
		
//		confList = FileTest.parseFile("iOS注册用户(非深圳市签约小区).txt");//ok
//		confList = FileTest.parseFile("iOS注册用户(深圳市非签约小区).txt");//ok
//		confList = FileTest.parseFile("iOS注册用户(深圳市签约小区).txt");//ok
		
//		confList = FileTest.parseFile("iOS.txt");//ok
//		confList = FileTest.parseFile("C:\\Users\\yewj\\Desktop\\百度推送\\iOS用户百度推送.txt");//ok
		
//		confList = FileTest.parseFile("C:\\Users\\yewj\\Desktop\\百度推送\\20160115\\嘉宝丰物业小区未缴费信息push.csv");//ok
//		confList = FileTest.parseFile("C:\\Users\\yewj\\Desktop\\百度推送\\20160115\\抱朴物业小区未缴费信息push.csv");//ok
//		confList = FileTest.parseFile("C:\\Users\\yewj\\Desktop\\百度推送\\楼村物业百度id.csv");//ok
		confList.add(new PushConfig(2, "725399635682359694", "5280789176935557029"));//weijian
//		confList.add(new PushConfig(1, "1114475557926467743", "3569219902791423336"));
//		confList.add(new PushConfig(1, "687045233074706433", "3941877722388971446"));
		TestPushOffline.push(title, content, confList);
	}


//	static{//ios 2
//		confList.add(new PushConfig(2, "629898491795545447", "4906209536852417444"));
//	}
	
	static{//android 1
		
	}
	
}
