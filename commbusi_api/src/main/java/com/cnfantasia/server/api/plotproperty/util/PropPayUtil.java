/**   
* Filename:    PropPayUtil.java   
* @version:    1.0  
* Create at:   2015年8月4日 下午3:41:54   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月4日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.util;

import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

/**
 * Filename:    PropPayUtil.java
 * @version:    1.0.0
 * Create at:   2015年8月4日 下午3:41:54
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月4日       shiyl             1.0             1.0 Version
 */
public class PropPayUtil {
	
	public static boolean canPayProp(GroupBuilding gb){
		boolean res = false;
		if(gb!=null){
			Integer propfeeCanpay = gb.getPropfeeCanpay();
			if(propfeeCanpay!=null&&propfeeCanpay.compareTo(RoomDict.GroupBuilding_CanPayProp.YES)==0){
				res =  true;
			}
		}
		return res;
	}
	
}
