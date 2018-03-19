/**   
* Filename:    IMerchantClaimService.java   
* @version:    1.0  
* Create at:   2015年1月28日 上午2:06:05   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;

/**
 * Filename:    IMerchantClaimService.java
 * @version:    1.0.0
 * Create at:   2015年1月28日 上午2:06:05
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月28日       shiyl             1.0             1.0 Version
 */
public interface IMerchantClaimService {
	/**
	 * 查询新增商家可选类别列表
	 * @return
	 */
	public List<CommunitySupplyType> getCommunitySupplyTypeCanAddList();

	/**
	 * 新增商家
	 * @param supplyTypeId
	 * @param supplyName
	 * @param addressBlockId
	 * @param addressDetail
	 * @param supplyContectInfo
	 * @param descriptPicList
	 * @param merchantPhone
	 * @param merchantLicensePicList
	 */
	public void addMerchant(BigInteger userId,BigInteger supplyTypeId, String supplyName, BigInteger addressBlockId, String addressDetail,
			String supplyContectInfo, List<RequestFileEntity> descriptPicList, String merchantPhone,
			List<RequestFileEntity> merchantLicensePicList);


	/**
	 * 用户认领商家
	 * @param userId
	 * @param groupBuildSupplyRelaId
	 * @param merchantPhone
	 * @param merchantLicensePicList
	 */
	public void doClaimMerchant(BigInteger userId, BigInteger groupBuildSupplyRelaId, String merchantPhone,
			List<RequestFileEntity> merchantLicensePicList);
	
}
