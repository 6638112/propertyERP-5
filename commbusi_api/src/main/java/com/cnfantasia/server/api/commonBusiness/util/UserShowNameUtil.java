/**   
* Filename:    UserShowNameUtil.java   
* @version:    1.0  
* Create at:   2014年12月23日 上午4:12:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.util;

import com.cnfantasia.server.domainbase.user.entity.User;

/**
 * Filename:    UserShowNameUtil.java
 * @version:    1.0.0
 * Create at:   2014年12月23日 上午4:12:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月23日       shiyl             1.0             1.0 Version
 */
public class UserShowNameUtil {
	
	public static String getUserShowName(User user){
		if(user==null||user.getId()==null){return null;}
		String resName = user.getId()+"";
		if(user!=null){
			resName = user.getNickName()!=null?user.getNickName():(user.getHuaId()!=null?user.getHuaId():(user.getId()))+"";
		}
		return resName;
	}
	
}
