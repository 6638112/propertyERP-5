/**   
* Filename:    NoticeDict.java   
* @version:    1.0  
* Create at:   2014年9月22日 下午12:56:22   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.notice.constant;

/**
 * Filename:    NoticeDict.java
 * @version:    1.0.0
 * Create at:   2014年9月22日 下午12:56:22
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月22日       shiyl             1.0             1.0 Version
 */
public class NoticeDict {
	
	public static class Message_SendStatus{
		/**未发送*/
		public static final Integer UnDo = Integer.valueOf("0");
		/**发送成功*/
		public static final Integer SendSuccess = Integer.valueOf("1");
		/**发送失败*/
		public static final Integer SendFailed = Integer.valueOf("2");
	}
	
	/**
	 * 消息状态=={"0":"未读","1":"已读"}
	 */
	public static class Message_ReadStatus{
		/**未读*/
		public static final Integer NotRead = Integer.valueOf("0");
		/**已读*/
		public static final Integer HasRead = Integer.valueOf("1");
	}
	
	/**消息类型{"3":"物业公告","4":"门牌验证","5":"折扣信息"}*/
	public static class Message_Type{
		/**物业公告*/
		public static final Long Property = Long.valueOf("3");
		/**门牌验证*/
		public static final Long Room = Long.valueOf("4");
		/**折扣信息*/
		public static final Long Discount = Long.valueOf("5");

		/*物业报修推送到解放区APP*/
		public static final Long Property_Repair_APP = 998L;
		
		/**物业报修师傅端*/
		public static final Long PropertyRepair = Long.valueOf("1001");
		/**上门维修师傅端*/
		public static final Long Dredge = Long.valueOf("1002");

		/**一元夺宝 通知中奖状态*/
		public static final Long FlashDeal = Long.valueOf("698");
		/**一元夺宝 提醒状态*/
		public static final Long FlashDealRemind = Long.valueOf("699");
		/**
		 * 商家发起限时购
		 */
		public static final Long MerchantAddLimitBuy = 700L;
		
		/**
		 * 寻求帮助回复，跳到消息通知
		 */
		public static final Long NoticeMessage = 701L;
		
		/**
		 * 运营消息推送 added by wenfq 2017-06-09
		 */
		public static final Long OperateMessage = 702L;


		/**拼一拼详情*/
		public static final Long PinyipinPush = Long.valueOf("21");
		/**换一换详情*/
		public static final Long ExchangePush = Long.valueOf("22");
		/**微博详情*/
		public static final Long MicroBlogPush = Long.valueOf("23");
		
		/**拼一拼评论列表*/
		public static final Long Pinyipin_CommentList = Long.valueOf("31");
		/**换一换评论列表*/
		public static final Long Exchange_CommentList = Long.valueOf("32");
		
		/**"41":"家庭成员列表"*/
		public static final Long Family_Member_List = Long.valueOf("41");
		/**"42":"跳转到我的小区"*/
		public static final Long To_My_GroupBuilding = Long.valueOf("42");
		/**"43":"门牌审核详情"*/
		public static final Long Room_Validate_Detail = Long.valueOf("43");
		/**"51":神码粮票*/
		public static final Long SM_Redenvelope = Long.valueOf("51");

        /**"61":家庭菜谱变更*/
		public static final Long Cookbook_List_changed = Long.valueOf("61");
       
        /**"71":新留言*/
        public static final Long You_have_a_new_msg = Long.valueOf("71");
        
		/**"81":车禁缴费到期提醒*/
        @Deprecated
		public static final Long Car_bill_expire_msg = Long.valueOf("81");
        /**"82":车禁缴费到期提醒*/
		public static final Long Car_expire_msg = Long.valueOf("82");

		public static final Long Property_Repair_Closed = 201L;
	}
	
}
