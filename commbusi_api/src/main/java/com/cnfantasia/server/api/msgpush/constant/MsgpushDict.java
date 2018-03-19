/**   
* Filename:    MsgpushDict.java   
* @version:    1.0  
* Create at:   2014年9月22日 上午3:04:57   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.msgpush.constant;

/**
 * Filename:    MsgpushDict.java
 * @version:    1.0.0
 * Create at:   2014年9月22日 上午3:04:57
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月22日       shiyl             1.0             1.0 Version
 */
public class MsgpushDict {
	/**
	 * 推送类型，取值范围为：1～3 
	 * 1：单个人，必须指定user_id 和 channel_id （指定用户的指定设备）或者user_id（指定用户的所有设备）
	 * 2：一群人，必须指定 tag
	 * 3：所有人，无需指定tag、user_id、channel_id 
	 */
	public static class BaiDu_push_type{
		public static final String Signal="1";
		public static final String Group="2";
		public static final String All="3";
	}
	
	/**
	 * 设备类型，取值范围为：1～5 
	 * 1：浏览器设备；
	 * 2：PC设备；
	 * 3：Andriod设备；
	 * 4：iOS设备；
	 * 5：Windows Phone设备； 
	 */
	public static class BaiDu_device_type{
//		public static final String Browser="1";
//		public static final String PC="2";
		public static final String Andriod="3";
		public static final String iOS="4";
//		public static final String WindowsPhone="5";
	}
	
	/**
	 * 消息类型
	 * 0：消息（透传给应用的消息体）
	 * 1：通知（对应设备上的消息通知）默认值为0。 
	 */
	public static class BaiDu_message_type{
		public static final String Message="0";
		public static final String Notice="1";
	}
	
	public static final class PushId{
        public static final String CookBookChanged="1";
        public static final String NewMessage="2";
        @Deprecated
        public static final String CarBillMsg="3";
		public static final String PropertyRepairBill_Closed = "4";
		public static final String WyNotice = "5"; //物业公告
		public static final String CarMsg = "6";
		public static final String MerchantAddLimitBuy = "7";
		
		/**
		 * 寻求帮助回复，跳到消息通知
		 */
		public static final String NoticeMessage = "8";
		
		/**
		 * 运营消息  added by wenfq 2017-06-08
		 */
		public static final String OperateMessge = "9";

		/**
         * 上门维修-新增单
         */
        public static final String DredgeBill_AddNew="100"; 
        /**
         * 上门维修-用户付款
         */
        public static final String DredgeBill_HasPayed="101"; 
        /**
         * 物业维修-派单
         */
        public static final String PropertyRepairBill_Assigned = "200";

		/**
		 * 一元夺宝派奖
		 */
		public static final String FlashDealActivity_Settle = "300";
		/**
		 * 一元夺宝开始提醒
		 */
		public static final String FlashDealActivity_remind = "301";

	}

	public static class PushReleatRoom {
		public static final Integer FALSE = 0;
		public static final Integer TRUE = 1;
	}
	
}
