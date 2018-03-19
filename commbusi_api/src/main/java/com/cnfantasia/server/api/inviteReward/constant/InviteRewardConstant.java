/**   
* Filename:    LoginConstant.java   
* @version:    1.0  
* Create at:   2014年8月8日 上午7:14:29   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.inviteReward.constant;


/**
 * 邀请奖励记录常量
 */
public class InviteRewardConstant {
	// 记录类型=={1:邀请人注册奖励,2:邀请人验证门牌奖励,3新人注册奖励,4新人验证门牌奖励}
	public static class Record_Type {
		/** 邀请人注册奖励 */
		public static final int IRW_1 = 1;
		/** 邀请人验证门牌奖励 */
		public static final int IVRW_2 = 2;
		/** 新人注册奖励 */
		public static final int RW_3 = 3;
		/** 新人验证门牌奖励 */
		public static final int VRW_4 = 4;
	}
	
	// 配置常量类型=={1:邀请人注册奖励,2:邀请人验证门牌奖励,3新人注册奖励,4新人验证门牌奖励}
	public static class Config_Type {
		/** 邀请人注册奖励 */
		public static final String BONUS_1 = "SMInviteBonus";
		/** 新人注册奖励 */
		public static final String BONUS_2 = "SMRegisterBonus";
		/** 邀请人验证门牌奖励 */
		public static final String BONUS_3 = "SMInviteVerifyRommBonus";
		/** 新人验证门牌奖励 */
		public static final String BONUS_4 = "SMRegisterVerifyRoomBonus";
		/** 玫瑰园邀请人注册奖励 */
		public static final String MGY_REG1 = "MGY_InviteBonus";
		/** 玫瑰园新人注册奖励 */
		public static final String MGY_REG2 = "MGY_RegisterBonus";
	}
	
	// 邀请类型=={"1":"解放区","2":"物业","3":"商家","4":"小区(玫瑰园)"}
	public static class Invite_Type {
		/** 解放区邀请 */
		public static final int Invite_1 = 1;
		/** 物业邀请 */
		public static final int Invite_2 = 2;
		/** 商家邀请 */
		public static final int Invite_3 = 3;
		/** 小区(玫瑰园) */
		public static final int Invite_4 = 4;
	}
	
	//解放区注册派奖过期时间
	public static String Invite_Overdue_Date = "Invite_Overdue_Date";
	//玫瑰园注册派奖过期时间
	public static String MGY_Invite_Overdue_Date = "MGY_Invite_Overdue_Date";
}

