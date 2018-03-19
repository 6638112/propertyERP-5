/**   
* Filename:    CommConstants.java   
* @version:    1.0  
* Create at:   2014年6月3日 下午12:31:41   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月3日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.common;

/**
 * Filename:    CommConstants.java
 * @version:    1.0.0
 * Create at:   2014年6月3日 下午12:31:41
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月3日       shiyl             1.0             1.0 Version
 */
public class CommConstants {
	public static final String DEFAULT_SYS_ERRCODE = "system.error";
	public static final String DEFAULT_SYS_ERRCODE_TIME_OUT = "sessionTimeout.error";
	
	/**
	 * 响应结果状态信息
	 */
	public class ResponseStatus{
		/**成功*/
		public static final String SUCCESS="0000";
		/**校验异常*/
		public static final String VALIDATE_ERR="0001";
		/**系统异常*/
		public static final String SYSTEM_ERR="0002";
		/**登录超时*/
		public static final String LOGIN_TIME_OUT="0003";
		/**业务处理异常*/
		public static final String BUSINESS_FAILED="0004";
		/**通讯异常*/
		public static final String COMMUNICATE_ERR="9***";
	}
	
	/**数据字典的统一前缀(用于根据messages.properties得到对应的字典定义)*/
  public static final String DICT_SRC_KEY_PRE="DICT";
  /**拼装结果数据时，对应的字典数据命名统一附加的后缀*/
  public static final String DICT_RES_KEY_TAIL="_DICT";
}
