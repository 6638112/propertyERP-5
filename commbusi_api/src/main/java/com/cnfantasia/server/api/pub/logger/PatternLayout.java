/**   
* Filename:    PatternLayout.java   
* @version:    1.0  
* Create at:   2014年8月8日 上午4:27:31   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub.logger;

import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.logger.AbstractPatternLayout;

/**
 * Filename:    PatternLayout.java
 * @version:    1.0.0
 * Create at:   2014年8月8日 上午4:27:31
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月8日       shiyl             1.0             1.0 Version
 */
public class PatternLayout extends AbstractPatternLayout{

	@Override
	public String getPrefixInfo() {
		// 取得用户ID
		String operId = UserContext.getOperId();
		if (operId == null) {
			operId = "";
		}
		String prefixInfo = "[" + operId + "]";
		return prefixInfo;
	}
}
