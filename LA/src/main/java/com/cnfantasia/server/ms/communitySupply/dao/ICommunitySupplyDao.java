package com.cnfantasia.server.ms.communitySupply.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.communitySupply.dao.ICommunitySupplyBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyEntity;

public interface ICommunitySupplyDao extends ICommunitySupplyBaseDao {

	public List<CommunitySupplyEntity> select_csList_byOmsUser(Map<String, Object> paramMap);

	public int deletePropertySuggestCommsupply_byCSId(String csId, String pcId, String gbId);

	/**
	 * 根据条件查询(社区商家类别)信息,精确查询
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<CommunitySupplyType> getCommunitySupplyTypeByCondition(Map<String, Object> paramMap);

	public int getCommunitySupply_byUserId_forCount(Map<String, Object> paramMap);

	public List<CommunitySupplyEntity> getCommunitySupplyList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);
}
