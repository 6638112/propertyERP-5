/**   
* Filename:    PrizeRuleDict.java   
* @version:    1.0  
* Create at:   2014年12月31日 上午8:18:49   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRule.constant;

/**
 * Filename:    PrizeRuleDict.java
 * @version:    1.0.0
 * Create at:   2014年12月31日 上午8:18:49
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月31日       shiyl             1.0             1.0 Version
 */
public class PrizeRuleDict {
	
	/**配置类型=={"1":"用户数","2":"月份"}*/
	public static enum ConfigType{
//		public static final Integer UserCount = 1;
//		public static final Integer Time = 2;
		UserCount_Sign(11)
		,Time_Sign(12)
		,UserCount_UnSign(21)
		,Time_UnSign(22)
		;
		
		private ConfigType(Integer value){
			this.value = value;
		}
		
		private Integer value;
		public Integer getValue() {
			return value;
		}
		public void setValue(Integer value) {
			this.value = value;
		}
		
	}
	
}
