/**   
* Filename:    SurpriseGiftDict.java   
* @version:    1.0  
* Create at:   2015年3月27日 上午8:01:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月27日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.surpriseGift.constant;

/**
 * Filename:    SurpriseGiftDict.java
 * @version:    1.0.0
 * Create at:   2015年3月27日 上午8:01:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月27日       shiyl             1.0             1.0 Version
 */
public class SurpriseGiftDict {
	
	/**
	 * 图标类别=={"1":"首次抽奖图标","2":"抽奖粮票"}
	 */
	public static class SurpriseGiftPic_Type{
		public static final Long FirstPrize = 1L;
		public static final Long HbPrize = 2L;
	}
	
	/**
	 * 惊喜数据类型=={"1":"粮票,消费券","2":"商品","3":"怡宝券","4":"58家政券"}
	 * {"1":"粮票,消费券","2":"商品","3":"怡宝桶装水兑换券","4":"58到家家政服务券","5":"电影票","6":"租车券","99":"市场推广特定奖项"}
	 */
	public static class PrizeSurpriseGift_DataType{
		public static final Integer HBAmount = 1;
		public static final Integer EbuyProduct = 2;
		public static final Integer YiBaoTicket = 3;
		public static final Integer FmlTicket58 = 4;
		public static final Integer MovieTicket = 5;
		public static final Integer AT_Taxi = 6;
		public static final Integer MARKET_OPT = 99;
	}
	
	/**
	 * 领取状态=={"1":"未领取","2":"已领取"}
	 */
	public static class PrizeSurpriseGift_FetchStatus{
		public static final Integer NotFetch = 1;
		public static final Integer Fetched = 2;
	}
	
	/**
	 * 来源类型=={"1":"临时用户抽奖","2":"注册用户抽奖","3":"分享"}
	 * 3:分享方式；4：轻应用推广链接；5：轻应用抽奖意外惊喜
	 */
	public static class PrizeSurpriseGift_FromType{
		public static final Integer TmpUserPrize = 1;
		public static final Integer RegistUserPrize = 2;
		public static final Integer Share = 3;
		/**轻应用推广链接*/
		public static final Integer LightApp_TGLJ = 4;
		/**轻应用抽奖意外惊喜*/
		public static final Integer LightApp_Prize = 5;
		/**待坊活动*/
		public static final Integer Neighbour_Activity = 6;
	}
	
	/**
	 * 使用状态=={"1":"未使用","2":"已使用","3":"已过期"}
	 */
	public static class PrizeSurpriseGift_UseStatus{
		public static final Integer NotUse = 1;
		public static final Integer HaveUsed = 2;
		public static final Integer Expired = 3;
	}
	
}
