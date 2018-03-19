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
package com.cnfantasia.server.ms.notice.constant;

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
		/**"43":"门牌审核详情"*/
		public static final Long Room_Validate_Detail = Long.valueOf("43");
		/**"51":神码粮票*/
		public static final Long SM_Redenvelope = Long.valueOf("51");
	}
}
