/**   
* Filename:    AutoFetchServiceUtil.java   
* @version:    1.0  
* Create at:   2014年11月28日 上午11:48:47   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.util;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.entity.GroupBuildingHasTCommunitySupply;

/**
 * Filename:    AutoFetchServiceUtil.java
 * @version:    1.0.0
 * Create at:   2014年11月28日 上午11:48:47
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月28日       shiyl             1.0             1.0 Version
 */
public class AutoFetchServiceUtil {
	
	/**
	 * 通过美丽加shopId 查询已存在的商家Id 如果shopId相同，则返回这个商家的Id
	 * 
	 * @param name
	 * @return
	 */
	public static BigInteger getIdByMljiaShopId(String currShopId, BigInteger groupBuildingId,
			List<CommunitySupply> communitySupplyList, List<CommunitySupply> communitySupplyList_db) {
		currShopId = currShopId.trim();
		for (CommunitySupply tmpCommunitySupply : communitySupplyList) {
			if(StringUtils.isEmpty(tmpCommunitySupply.getMljiaShopId())){continue;}
			if (tmpCommunitySupply.getMljiaShopId().trim().equals(currShopId)) {
				return tmpCommunitySupply.getId();
			}
		}
		for (CommunitySupply tmpCommunitySupply : communitySupplyList_db) {
			if(StringUtils.isEmpty(tmpCommunitySupply.getMljiaShopId())){continue;}
			if (tmpCommunitySupply.getMljiaShopId().trim().equals(currShopId)) {
				return tmpCommunitySupply.getId();
			}
		}
		return null;
	}
	
	/**
	 * 通过商家名称查询已存在的Id 如果商家名称相同且都属于同一个小区，则返回这个商家的Id
	 * 
	 * @param name
	 * @return
	 */
	public static BigInteger getIdByCommunitySupplyName(String supplyName, BigInteger groupBuildingId,
			List<CommunitySupply> communitySupplyList, List<CommunitySupply> communitySupplyList_db,
			List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList,
			List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList_db) {
		for (CommunitySupply tmpCommunitySupply : communitySupplyList) {
			if (tmpCommunitySupply.getName().trim().equals(supplyName)) {
				for (GroupBuildingHasTCommunitySupply tmpGroupBuildingHasTCommunitySupply : groupBuildingHasTCommunitySupplyList) {
					if (tmpGroupBuildingHasTCommunitySupply.gettGroupBuildingFId().compareTo(groupBuildingId) == 0
							&& tmpGroupBuildingHasTCommunitySupply.gettCommunitySupplyFId().compareTo(tmpCommunitySupply.getId()) == 0) {
						return tmpCommunitySupply.getId();
					}
				}
			}
		}
		for (CommunitySupply tmpCommunitySupply : communitySupplyList_db) {
			if (tmpCommunitySupply.getName().trim().equals(supplyName)) {
				for (GroupBuildingHasTCommunitySupply tmpGroupBuildingHasTCommunitySupply : groupBuildingHasTCommunitySupplyList_db) {
					if (tmpGroupBuildingHasTCommunitySupply.gettGroupBuildingFId().compareTo(groupBuildingId) == 0
							&& tmpGroupBuildingHasTCommunitySupply.gettCommunitySupplyFId().compareTo(tmpCommunitySupply.getId()) == 0) {
						return tmpCommunitySupply.getId();
					}
				}
			}
		}
		return null;
	}

	/**
	 * 通过商家Id+联系方式，查询对应记录的Id
	 */
	public static BigInteger getIdByCommunitySupplyAndCommunityContect(BigInteger communitySupplyId, String contectInfo,
			List<CommunitySupplyContect> communitySupplyContectList,
			List<CommunitySupplyContect> communitySupplyContectList_db) {
		for (CommunitySupplyContect tmpCommunitySupplyContect : communitySupplyContectList) {
			if (tmpCommunitySupplyContect.gettCommunitySupplyFId().compareTo(communitySupplyId) == 0
					&& tmpCommunitySupplyContect.getContectInfo().trim().equals(contectInfo)) {
				return tmpCommunitySupplyContect.getId();
			}
		}
		for (CommunitySupplyContect tmpCommunitySupplyContect : communitySupplyContectList_db) {
			if (tmpCommunitySupplyContect.gettCommunitySupplyFId().compareTo(communitySupplyId) == 0
					&& tmpCommunitySupplyContect.getContectInfo().trim().equals(contectInfo)) {
				return tmpCommunitySupplyContect.getId();
			}
		}
		return null;
	}

	/**
	 * 通过小区Id+商家Id，查询对应记录的Id
	 */
	public static BigInteger getIdByGroupBuildingAndCommunitySupply(BigInteger groupBuildingId, BigInteger communitySupplyId,
			List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList,
			List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList_db) {
		// System.out.println("groupBuildingId="+groupBuildingId+",communitySupplyId"+communitySupplyId);
		for (GroupBuildingHasTCommunitySupply tmpGroupBuildingHasTCommunitySupply : groupBuildingHasTCommunitySupplyList) {
			if (tmpGroupBuildingHasTCommunitySupply.gettGroupBuildingFId().compareTo(groupBuildingId) == 0
					&& tmpGroupBuildingHasTCommunitySupply.gettCommunitySupplyFId().compareTo(communitySupplyId) == 0) {
				return tmpGroupBuildingHasTCommunitySupply.getId();
			}
		}
		for (GroupBuildingHasTCommunitySupply tmpGroupBuildingHasTCommunitySupply : groupBuildingHasTCommunitySupplyList_db) {
			if (tmpGroupBuildingHasTCommunitySupply.gettGroupBuildingFId().compareTo(groupBuildingId) == 0
					&& tmpGroupBuildingHasTCommunitySupply.gettCommunitySupplyFId().compareTo(communitySupplyId) == 0) {
				return tmpGroupBuildingHasTCommunitySupply.getId();
			}
		}
		return null;
	}
}
