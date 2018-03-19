/**   
 * Filename:    DictConstants.java   
 * @version:    1.0  
 * Create at:   2014年5月7日 上午1:32:41   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年5月7日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.pub.constant;

/**
 * Filename: DictConstants.java
 * 
 * @version: 1.0.0 Create at: 2014年5月7日 上午1:32:41 Description:数据字典常量类
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年5月7日 shiyl 1.0 1.0 Version
 */
public class DictConstants {
	
	/**
	 * subChannelId,渠道标识
	 */
	public static class Channel_Sub {
		/** 安卓 */
		public static final Integer ANDROID = 1;
		/** IOS */
		public static final Integer IOS = 2;
		/** 商户端*/
		public static final Integer OOS = 9;
		/** 商户端*/
		public static final Integer EBUY_MERCHANT = 20;
	}

	// /**
	// * 用户门牌关系状态
	// */
	// public static class UserHasTRoom_Status{
	// public static final Long IN_USE = 0L;
	// public static final Long DIS_ABLE = 1L;
	// }

	// /**
	// * 产品图片表中的图片大小类型
	// */
	// public static class EbuyProductPic_Type{
	// /**大图片*/
	// public static final Integer BIG = 0;
	// /**下图片*/
	// public static final Integer SMALL = 1;
	// }

	// /**
	// * 历史记录表操作类型
	// */
	// public static class CommonHistory_ACTION_TYPE{
	// /** 修改 */
	// public static final Integer UPDATE = 1;
	// /** 删除 */
	// public static final Integer DELETE = 2;
	// /** 新增 */
	// public static final Integer ADD = 3;
	// }

}
