/**   
* Filename:    PrizeRuleConstant.java   
* @version:    1.0  
* Create at:   2014年8月29日 上午3:28:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRule.constant;

/**
 * Filename:    PrizeRuleConstant.java
 * @version:    1.0.0
 * Create at:   2014年8月29日 上午3:28:27
 * Description: 抽奖规则配置常量
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月29日       shiyl             1.0             1.0 Version
 */
public class PrizeRuleConstant {
	/**总房间数，总折扣奖励数*/
//	public static final String TOTAL_ROOM_COUNT = "TotalRoomCount";
	/**每天最大抽奖次数*/
	public static final String MAX_PRIZE_COUNT_DAY = "MaxPrizeCountDay";
	/**兑奖规则a1取值*/
	public static final String PRIZE_CONVERT_RULE_A1 = "PrizeConvertRuleA1";
	/**兑奖规则d值*/
	public static final String PRIZE_CONVERT_RULE_D = "PrizeConvertRuleD";
	/**未中奖时返回的折扣*/
	public static final String UN_PRIZE_DISCOUNT = "UnPrizeDiscount";
	/**中奖允许的最大折扣*/
	public static final String PRIZE_DISCOUNT_MAX = "PrizeDiscountMax";
	/**A折扣区间标识Id*/
	public static final String PRIZE_AREA_A_ID="PrizeAreaAId";
	/**B折扣区间标识Id*/
	public static final String PRIZE_AREA_B_ID="PrizeAreaBId";
	/**C折扣区间标识Id*/
	public static final String PRIZE_AREA_C_ID="PrizeAreaCId";
	/**D折扣区间标识Id*/
	public static final String PRIZE_AREA_D_ID="PrizeAreaDId";
	/**E折扣区间标识Id*/
	public static final String PRIZE_AREA_E_ID="PrizeAreaEId";
	
	/**未中奖时判断比较的折扣值*/
	public static final String PRIZE_LASTCHECK_DISCOUNT="PrizeLastCheckDiscount";
	
	
	public static final Long PRIZE_DISCOUNT_DIVNUM=1000L;
}
