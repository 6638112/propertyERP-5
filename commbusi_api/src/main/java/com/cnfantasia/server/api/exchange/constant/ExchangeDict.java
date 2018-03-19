/**   
* Filename:    ExchangeDict.java   
* @version:    1.0  
* Create at:   2014年10月17日 上午6:16:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.exchange.constant;

/**
 * Filename:    ExchangeDict.java
 * @version:    1.0.0
 * Create at:   2014年10月17日 上午6:16:45
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月17日       shiyl             1.0             1.0 Version
 */
public class ExchangeDict {
	
	/**
	 * 换物所属用户=={"1":"发起者","2":"参与者"} 0:都不是
	 */
	public static class ExchangeContent_UserBelong{
		public static final Integer None = 0;//都不是
		public static final Integer Launcher = 1;
		public static final Integer Joiner = 2;
	}
	
	/**
	 * 换物关系的状态=={"1":"待交换","2":"已交换"} 
	 */
	public static class ExchangeRelation_Status{
		public static final Integer ToDo = 1;
		public static final Integer Finished = 2;
	}
	
	/**
	 * 换物状态=={"1":"闲置中","2":"可交换","3":"已交换"}
	 */
	public static class ExchangeContent_Status{
		public static final Integer IDLE = 1;
		public static final Integer ExchangeAble  = 2;
		public static final Integer Finished = 3;
	}
	
	/**
	 * 创建方式=={"0":"发起方式创建","1":"参与方式创建"}
	 */
	public static class ExchangeContent_CreateType{
		public static final Integer Launch = 0;
		public static final Integer JOIN  = 1;
	}
	
}
