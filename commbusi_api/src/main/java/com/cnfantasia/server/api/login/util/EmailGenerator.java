/**   
* Filename:    EmailGenerator.java   
* @version:    1.0  
* Create at:   2014年7月11日 上午3:56:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月11日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.login.util;


/**
 * Filename:    EmailGenerator.java
 * @version:    1.0.0
 * Create at:   2014年7月11日 上午3:56:19
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月11日       shiyl             1.0             1.0 Version
 */
public class EmailGenerator {
	public static String getTmpEmail(String huaId){
		return huaId+"@"+"tmp.com";
//		return "aaa@bb.com";
	}
}
