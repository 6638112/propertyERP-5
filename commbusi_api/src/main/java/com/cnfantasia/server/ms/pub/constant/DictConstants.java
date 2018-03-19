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
package com.cnfantasia.server.ms.pub.constant;

/**
 * Filename:    DictConstants.java
 * @version:    1.0.0
 * Create at:   2014年5月7日 上午1:32:41
 * Description:数据字典常量类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月7日       shiyl             1.0             1.0 Version
 */
public class DictConstants {
	/**
	 * 权限状态
	 */
	public static class PermiFunction_Status{
		/** 启用 */
		public static final Integer IN_USE = 0;
		/**  禁用 */
		public static final Integer DIS_ABLE = 1;
	}
	
	public static class Message_Type{
		
		public static final String NOTICE_MESSAGE_TYPE = "3";
		
	}
	
	public static class Delete_Status{
		public static final String NORMAL = "0";
		
		public static final String DELETED = "1";
	}
}
