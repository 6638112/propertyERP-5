/**   
* Filename:    EncryptDict.java   
* @version:    1.0  
* Create at:   2016年2月19日 下午1:44:24   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2016年2月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.encrypt.constant;

/**
 * Filename:    EncryptDict.java
 * @version:    1.0.0
 * Create at:   2016年2月19日 下午1:44:24
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2016年2月19日       shiyl             1.0             1.0 Version
 */
public class EncryptDict {
	
	/**
	 * 是否校验全部字段=={"0":"是","1":"否"}
	 */
	public static class EncryptUrl_AllColumn{
		public static final Integer YES = 0;
		public static final Integer NO = 1;
	}
	
	/**
	 * 加密行为=={"1":"MD5"}
	 */
	public static class EncryptUrl_SignType{
		public static final Integer MD5 = 1;
	}
}
