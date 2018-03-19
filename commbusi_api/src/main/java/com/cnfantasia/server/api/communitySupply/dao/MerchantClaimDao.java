/**   
* Filename:    MerchantClaimDao.java   
* @version:    1.0  
* Create at:   2015年1月28日 上午2:05:18   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyBelong.entity.CommunitySupplyBelong;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;

/**
 * Filename:    MerchantClaimDao.java
 * @version:    1.0.0
 * Create at:   2015年1月28日 上午2:05:18
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月28日       shiyl             1.0             1.0 Version
 */
public class MerchantClaimDao extends AbstractBaseDao implements IMerchantClaimDao{

	@Override
	public List<CommunitySupplyType> selectCommunitySupplyTypeCanAddList() {
		return sqlSession.selectList("merchantClaim.select_CommunitySupplyType_CanAdd_List");
	}

	@Override
	public CommunitySupplyBelong selectCommunitySupplyBelongSuccess(BigInteger groupBuilding_communitySupply_RelaId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("groupBuilding_communitySupply_RelaId", groupBuilding_communitySupply_RelaId);
		return sqlSession.selectOne("merchantClaim.select_CommunitySupplyBelong_Success",tmpMap);
	}
	
}
