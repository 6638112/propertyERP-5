/**   
* Filename:    ISysParamParser.java   
* @version:    1.0  
* Create at:   2014年5月26日 上午8:49:14   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.pub.sysParam;

/**
 * Filename:    ISysParamParser.java
 * @version:    1.0.0
 * Create at:   2014年5月26日 上午8:49:14
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月26日       shiyl             1.0             1.0 Version
 */
public interface ISysParamParser {
	public <T> T parseParamValue();
}
