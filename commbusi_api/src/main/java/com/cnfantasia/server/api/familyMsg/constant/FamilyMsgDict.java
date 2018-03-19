/**   
* Filename:    FamilyMsgDict.java   
* @version:    1.0  
* Create at:   2015年3月13日 上午8:17:31   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月13日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.familyMsg.constant;

/**
 * Filename:    FamilyMsgDict.java
 * @version:    1.0.0
 * Create at:   2015年3月13日 上午8:17:31
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月13日       shiyl             1.0             1.0 Version
 */
public class FamilyMsgDict {
	/**
	 * 数据类别标识=={"1":"家庭购物车商品","2":"单个商品","3":"今日菜单","4":"单个菜谱","5":"家庭折扣","6":"2015-9-11改版后的今日菜单"}
	 */
	public static class FamilyMsgExtData_type{
		public static final Integer None = 0;
		public static final Integer FamilyBuyCar = 1;
		public static final Integer SignalProduct = 2;
		public static final Integer TodayCookBook = 3;
		public static final Integer SignalCookBook = 4;
		public static final Integer Discount = 5;
        public static final Integer TodayCookBookNew = 6;
	}
	
	public static final Integer DEFAULT_COMMENTS_LENGTH = null;//为空表示取全部
}
