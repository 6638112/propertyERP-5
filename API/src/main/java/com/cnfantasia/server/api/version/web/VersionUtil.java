/**   
* Filename:    VersionUtil.java   
* @version:    1.0  
* Create at:   2016年1月28日 下午4:42:55   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2016年1月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.version.web;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.common.utils.ParamUtils;

/**
 * Filename:    VersionUtil.java
 * @version:    1.0.0
 * Create at:   2016年1月28日 下午4:42:55
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2016年1月28日       shiyl             1.0             1.0 Version
 */
public class VersionUtil {
	
	public static BigInteger getUserIdForUpd(HttpServletRequest request){
		BigInteger userId = UserContext.getOperIdBigInteger();
		if(userId==null){
			userId = ParamUtils.getBigInteger(request, "userId", null);
		}
		return userId;
	}
	
}
