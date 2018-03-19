/**   
 * Filename:    CommunitySupplyDataFetchUtil.java   
 * @version:    1.0  
 * Create at:   2014年11月28日 上午9:37:04   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年11月28日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.communitySupply.util;

import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename: CommunitySupplyDataFetchUtil.java
 * 
 * @version: 1.0.0 Create at: 2014年11月28日 上午9:37:04 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年11月28日 shiyl 1.0 1.0 Version
 */
public class CommunitySupplyDataFetchUtil {
	public static List<String> getSearchNameList(String supplyTypeName, String searchKey) {
		supplyTypeName = supplyTypeName.trim();
		List<String> resList = new ArrayList<String>();
		resList.add(supplyTypeName);
		if (!StringUtils.isEmpty(searchKey)) {
			searchKey = searchKey.trim();
			String[] arr = searchKey.split(";");
			for (String tmpStr : arr) {
				if (!StringUtils.isEmpty(tmpStr) && !tmpStr.trim().equals(supplyTypeName)) {
					resList.add(tmpStr);
				}
			}
		}
		return resList;
	}
}
