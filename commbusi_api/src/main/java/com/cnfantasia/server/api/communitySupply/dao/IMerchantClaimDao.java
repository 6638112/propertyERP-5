/**   
* Filename:    IMerchantClaimDao.java   
* @version:    1.0  
* Create at:   2015年1月28日 上午2:05:06   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.communitySupplyBelong.entity.CommunitySupplyBelong;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;

/**
 * Filename:    IMerchantClaimDao.java
 * @version:    1.0.0
 * Create at:   2015年1月28日 上午2:05:06
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月28日       shiyl             1.0             1.0 Version
 */
public interface IMerchantClaimDao {
	
	/**
	 * 查询新增商家可选类别列表
	 * @return
	 */
	public List<CommunitySupplyType> selectCommunitySupplyTypeCanAddList();
	
	/**
	 * 查询对应关系的成功的认领信息
	 * @param groupBuilding_communitySupply_RelaId 小区商家关系Id
	 * @return
	 */
	public CommunitySupplyBelong selectCommunitySupplyBelongSuccess(BigInteger groupBuilding_communitySupply_RelaId);
	
}
