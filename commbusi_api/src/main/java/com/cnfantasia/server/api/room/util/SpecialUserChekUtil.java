/**   
* Filename:    SpecialUserChekUtil.java   
* @version:    1.0  
* Create at:   2015年5月14日 上午9:13:28   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年5月14日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.room.util;

import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * Filename:    SpecialUserChekUtil.java
 * @version:    1.0.0
 * Create at:   2015年5月14日 上午9:13:28
 * Description:特使用户处理类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年5月14日       shiyl             1.0             1.0 Version
 */
public class SpecialUserChekUtil {
	
	public static boolean checkIsSpecialUser(UserHasTRoom userHasTRoom){
		boolean isSpecialUser = false;
		if(userHasTRoom!=null&&userHasTRoom.getPlanSwitchStatus()!=null
				&&RoomDict.UserHasTRoom_PlanSwitchStatus.OPEN.compareTo(userHasTRoom.getPlanSwitchStatus())==0
				&&userHasTRoom.getPlanPropertyAmount()!=null){
			isSpecialUser = true;
		}
		return isSpecialUser;
	}
	
}
