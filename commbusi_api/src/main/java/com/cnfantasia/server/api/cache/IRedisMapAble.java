/**   
* Filename:    IRedisMapAble.java   
* @version:    1.0  
* Create at:   2015年7月31日 下午12:59:37   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.cache;

import java.util.Map;

/**
 * Filename:    IRedisMapAble.java
 * @version:    1.0.0
 * Create at:   2015年7月31日 下午12:59:37
 * Description: 可转为redis存储的Map
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月31日       shiyl             1.0             1.0 Version
 */
public interface IRedisMapAble {
	
	public Map<String,String> toMap();
	
}
