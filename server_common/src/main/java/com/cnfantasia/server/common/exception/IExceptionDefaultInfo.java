/**   
* Filename:    IExceptionDefaultInfo.java   
* @version:    1.0  
* Create at:   2014年7月30日 上午9:57:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.common.exception;

import com.cnfantasia.server.common.exception.entity.ErrorInfoEntity;

/**
 * Filename:    IExceptionDefaultInfo.java
 * @version:    1.0.0
 * Create at:   2014年7月30日 上午9:57:45
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月30日       shiyl             1.0             1.0 Version
 */
public interface IExceptionDefaultInfo {
	
	public ErrorInfoEntity getDefaultErrCode();
	
}
