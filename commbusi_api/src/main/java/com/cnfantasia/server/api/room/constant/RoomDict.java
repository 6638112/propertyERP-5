/**   
 * Filename:    RoomDict.java   
 * @version:    1.0  
 * Create at:   2014年8月8日 上午7:56:34   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月8日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.room.constant;

/**
 * Filename: RoomDict.java
 * 
 * @version: 1.0.0 Create at: 2014年8月8日 上午7:56:34 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月8日 shiyl 1.0 1.0 Version
 */
public class RoomDict {
	/**
	 * 粮票优惠开启状态=={"1":"开启","2":"关闭"}
	 */
	public static class UserHasTRoom_PlanSwitchStatus {
		/** "1":"开启" */
		public static final Integer OPEN = 1;
		/** "2":"关闭"*/
		public static final Integer CLOSE = 2;
	}
	
	/**
	 * 小区签约状态
	 */
	public static class GroupBuilding_SignStatus {
		/** 未签约 */
		public static final Integer NOT_SIGN = 0;
		/** 已签约 */
		public static final Integer IS_SIGN = 1;
	}
	
	/**
	 * 小区是否可缴纳物业费
	 */
	public static class GroupBuilding_CanPayProp {
		/** 是 */
		public static final Integer YES = 1;
		/** 否 */
		public static final Integer NO = 2;
	}
	
	/**
	 * 门牌申请加入状态=={"0":"未处理","1":"已通过","2":"已拒绝"}
	 */
	public static class UserHasTRoom_ApplyStatus {
		/** 未处理 */
		public static final Integer UNDO = 0;
		/** 已通过*/
		public static final Integer SUCCESS = 1;
		/** 已拒绝 */
		public static final Integer REJECT = 2;
	}
	
	/**
	 * 用户对真实门牌的验证状态=={"1":"未验证","5":"已验证"}
	 */
	public static class RealRoom_ValidateStatus {
		/** 未验证*/
		public static final Integer NOT_VALIDATED = 1;
		/** 已验证*/
		public static final Integer IS_VALIDATED = 5;
	}
	
	public static class CheckStatus{
		/**待审核*/
		public static final Integer DaiShenHe = 0;
		/**审核通过*/
		public static final Integer ShenHeTongGuo = 1;
		/**审核不通过*/
		public static final Integer ShenHeBuTongGuo = 2;
		/**无需审核*/
		public static final Integer WuXuShenHe = 9;
	}
	
	/**
	 * 虚拟门牌对应的
	 * 真实门牌对应的 真实门牌是否已经被确认
	 * {"1":"未确认","2":"已确认"}
	 */
	public static class Room_RealRoomCheckStatus{
		/**未确认*/
		public static final Integer UnConfirm = 1;
		/**已确认*/
		public static final Integer IsConfirmed = 2;
	}
	
	/**
	 * 用户门牌是否为管理员
	 */
	public static class UserHasTRoom_IsAdmin{
		public static final Integer TRUE = 0;
		public static final Integer FALSE = 1;
	}
	
	/**
	 * 门牌验证状态
	 */
	public static class RoomValidte_VerifyStatus{
		/**未验证*/
		public static final Integer UNDO = 1;
		/**验证中*/
		public static final Integer DOING = 2;
		/**验证失败*/
		public static final Integer FAILED = 3;
		/**验证通过*/
		public static final Integer SUCCESS = 4;
	}
	
	/**
	 * 门牌验证-验证信息来源类型 {"1":"提交验证资料","2":"简单申请加入","3":"缴纳物业费自动添加","4":"面对面邀请自动添加","5":"门牌管理员通过客户端决策"}
	 */
	public static class RoomValidte_SourceType{
		/**"1":"提交验证资料"*/
		public static final Integer Submit_File = 1;
		/**"2":"简单申请加入"*/
		public static final Integer Simple_Join = 2;
		/**"3":"缴纳物业费自动添加"*/
		public static final Integer PayBill_Auto_Add = 3;
		/**"4":"面对面邀请自动添加"*/
		public static final Integer Face2Face_Auto_Add = 4;
		/**"5":"门牌管理员通过客户端允许"*/
		public static final Integer Admin_Decide_ByClient_ = 5;
		/**"6":"成功匹配业主手机号，门牌自动验证通过"*/
		public static final Integer RoomOwnerPhone_Equal_Auto_Add = 6;
		/**"7":"购买物业宝自动验证通过"*/
		public static final Integer BUY_FINANCE = 7;
		
		/**"8":"物业代扣卡划扣成功，自动验证通过"*/
		public static final Integer Property_Card = 8;
	}
	
	/**
	 * 门牌是否为首次使用粮票的标识
	 */
	public static class FirstHbconvertState{
		public static final Integer YES = 1;
		public static final Integer NO = 2;
	}
	
}
