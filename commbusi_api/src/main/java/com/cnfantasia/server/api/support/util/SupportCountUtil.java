/**   
* Filename:    SupportCountUtil.java   
* @version:    1.0  
* Create at:   2015年4月10日 上午9:00:21   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月10日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.support.util;

import com.cnfantasia.server.api.room.constant.GroupBuildingDict;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

/**
 * Filename:    SupportCountUtil.java
 * @version:    1.0.0
 * Create at:   2015年4月10日 上午9:00:21
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月10日       shiyl             1.0             1.0 Version
 */
public class SupportCountUtil {
	public static int getSupportCount(int count,GroupBuilding currGroupBuilding){
		if(currGroupBuilding!=null&&currGroupBuilding.getCheckStatus().compareTo(GroupBuildingDict.CheckStatus.ShenHeTongGuo)==0
				||currGroupBuilding!=null&&currGroupBuilding.getCheckStatus().compareTo(GroupBuildingDict.CheckStatus.WuXuShenHe)==0){
			count = count+80;
		}
		return count;
	}
}
