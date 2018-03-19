/**   
* Filename:    MicroblogQueueDict.java   
* @version:    1.0  
* Create at:   2015年8月31日 上午10:55:43   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.microblogQueue.constant;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Filename:    MicroblogQueueDict.java
 * @version:    1.0.0
 * Create at:   2015年8月31日 上午10:55:43
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月31日       shiyl             1.0             1.0 Version
 */
public class MicroblogQueueDict {
		//消息来源=={1:"用户",2:"商家",3:"物业",4:"解放区运营"}
		public static class Source_Type {
			/** 用户 */
			public static final int USER = 5;
			/** 商家 */
			public static final int CS = 2;
			/** 物业 */
			public static final int PC = 3;
			/** 解放区运营 */
			public static final int JFQ = 4;
		}
		
		// 是否定时消息=={1:"定时消息",0:"及时消息"}
		public static class Timing {
			/** 定时消息 */
			public static final int YES = 1;
	//		/** 及时消息 */
	//		public static final int NO = 0;
		}
		
		/**是否可被推送到微博表=={"0":"否","1":"是"}*/
		public static class PushAble {
			/** 否 */
			public static final int NO = 0;
			/** 是 */
			public static final int YES = 1;
		}
		
		// 推送状态=={1:"成功推送",0:"未推送"}
		public static class Push_Status {
			/** 解放区邀请 */
			public static final int YES = 1;
			/** 物业邀请 */
			public static final int NO = 0;
		}
		
		// 消息Push用户ID=={10000:"Admin",10001:"用户管理员",10002:"商家管理员",10003:"物业管理员",10004:"解放区运营管理员"}
		public static class Sys_User_Id {
			/** 解放区 */
			public static final BigInteger ADMIN = new BigInteger("100");
			/** 商家 */
			public static final BigInteger CS = new BigInteger("200");
			/** 物业 */
			public static final BigInteger PC = new BigInteger("300");
		}
		
		// 博客图片展示类型=={10:"签约公告",20:"缴费提醒",30:"零折",40:"物业缴费",50:"商家接入",60:"通过验证",70:"物业公告",80:"购物免单",90:"最低折扣",100:"意外惊喜",110:"节假日",120:"人气商家",130:"邀请用户奖励",140:"信息发布奖励",150:"新增用户"}
		public static class MQ_Level {
	//		/** 自定义等级 */
	//		public static final Long Code_1 = 1L;
			/** 签约公告 */
			public static final Long Code_10 = 10L;
			/** 缴费提醒 */
			public static final Long Code_20 = 20L;
			/** 零折 */
			public static final Long Code_30 = 30L;
			/** 物业缴费,喜报信息 */
			public static final Long Code_40 = 40L;
	//		/** 商家接入 */
	//		public static final Long Code_50 = 50L;
			/** 通过验证 */
			public static final Long Code_60 = 60L;
			/** 物业公告 */
			public static final Long Code_70 = 70L;
			/** 购物免单 */
			public static final Long Code_80 = 80L;
			/** 最低折扣 */
			public static final Long Code_90 = 90L;
	//		/** 意外惊喜 */
	//		public static final Long Code_100 = 100L;
	//		/** 节假日（中秋之类的） */
	//		public static final Long Code_110 = 110L;
	//		/** 人气商家 */
	//		public static final Long Code_120 = 120L;
	//		/** 邀请用户奖励 */
	//		public static final Long Code_130 = 130L;
	//		/** 信息发布奖励 */
	//		public static final Long Code_140 = 140L;
			/** 新增用户 */
			public static final Long Code_150 = 150L;
			
			/**编码对应的图片信息*/
			public static final Map<Long,String> Map_Code_Pic = new HashMap<Long,String>();
			static{
	//			Map_Code_Pic.put(Code_1, "");
				Map_Code_Pic.put(Code_10, "sys_xiaoquqianyue.jpg");//10:"签约公告"
				Map_Code_Pic.put(Code_20, "sys_jiaofeidaojishiriqi.jpg");//20:"缴费提醒"
				Map_Code_Pic.put(Code_30, "sys_0zhekou.jpg");//30:"零折"
				Map_Code_Pic.put(Code_40, "sys_wuyejiaofeishengqian.jpg");//40:"物业缴费"
	//			Map_Code_Pic.put(Code_50, "sys_xiaodiankaizhang.jpg");//50:"商家接入"
				Map_Code_Pic.put(Code_60, "sys_xinyonghurenzhengtongguo.jpg");//60:"通过验证"
				Map_Code_Pic.put(Code_70, "");//70:"物业公告"
				Map_Code_Pic.put(Code_80, "sys_gouwubuhuaqian.jpg");//80:"购物免单"
				Map_Code_Pic.put(Code_90, "sys_zuidizhekou.jpg");//90:"最低折扣"
	//			Map_Code_Pic.put(Code_100, "sys_fengxiangdejingxi.jpg");//100:"意外惊喜"
	//			Map_Code_Pic.put(Code_110, "");//110:"节假日"
	//			Map_Code_Pic.put(Code_120, "sys_xiaodianzuishouhuanying.jpg");//120:"人气商家"
	//			Map_Code_Pic.put(Code_130, "sys_yaoqingrendehongbao.jpg");//130:"邀请用户奖励"
	//			Map_Code_Pic.put(Code_140, "sys_jinlaba.jpg");//140:"信息发布奖励"
				Map_Code_Pic.put(Code_150, "sys_xinyonghuzhuce.jpg");//150:"新增用户"
			}
		}
		
}
