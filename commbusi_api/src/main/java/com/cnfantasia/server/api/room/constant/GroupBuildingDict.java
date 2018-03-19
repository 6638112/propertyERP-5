/**   
* Filename:    GroupBuildingDict.java   
* @version:    1.0  
* Create at:   2014年8月18日 上午3:57:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.room.constant;

/**
 * Filename:    GroupBuildingDict.java
 * @version:    1.0.0
 * Create at:   2014年8月18日 上午3:57:27
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月18日       shiyl             1.0             1.0 Version
 */
public class GroupBuildingDict {
	/**
	 * 物业账单提前月份数=={"0":"本月","1":"当月录下个月账单"}
	 */
	public static class GroupBuilding_PropertyMonthChange{
		/**"1":"当月录下个月账单"*/
		public static final Integer NextMonth = 1;
		/**"-1":"当月录上个月账单"*/
		public static final Integer PreMonth = -1;
	}
	
	
	/**
	 * 是否已经点支持，1是2否
	 */
	public static class GroupBuilding_IsSupport{
		public static final Integer YES = 1;
		public static final Integer NO = 2;
	}
	
	/**
	 * 是否已经点召唤，1是2否
	 */
	public static class GroupBuilding_IsSummon{
		public static final Integer YES = 1;
		public static final Integer NO = 2;
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
	 * 数据抓取状态=={"1":"未抓取","2":"已抓取"}
	 */
	public static class GroupBuilding_fetchStatus{
		public static final Integer NotFetch = 1;
		public static final Integer HasFetch = 2;
	}
	
}
