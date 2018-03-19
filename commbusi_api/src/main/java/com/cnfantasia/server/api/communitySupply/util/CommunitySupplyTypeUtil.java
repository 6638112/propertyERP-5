/**   
* Filename:    CommunitySupplyTypeUtil.java   
* @version:    1.0  
* Create at:   2015年8月19日 下午2:15:57   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.util;

import com.cnfantasia.server.api.communitySupply.constant.CommunitySupplyDict;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;

/**
 * Filename:    CommunitySupplyTypeUtil.java
 * @version:    1.0.0
 * Create at:   2015年8月19日 下午2:15:57
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月19日       shiyl             1.0             1.0 Version
 */
public class CommunitySupplyTypeUtil {
	
	/**
	 * 获取图标内容
	 * @param communitySupplyType
	 * @return
	 */
	public static String getIconName(CommunitySupplyType communitySupplyType){
		Integer level = communitySupplyType.getImportanceLevel();
		String iconUrl = null;
		if(CommunitySupplyDict.CommunitySupply_Type_Importance_Level.level01.compareTo(level)==0){
			iconUrl = communitySupplyType.getIconBigUrl();
		}else if(CommunitySupplyDict.CommunitySupply_Type_Importance_Level.level02.compareTo(level)==0){
			iconUrl = communitySupplyType.getIconSmallUrl();
		}
		return iconUrl;
	}
	
	
	
}
