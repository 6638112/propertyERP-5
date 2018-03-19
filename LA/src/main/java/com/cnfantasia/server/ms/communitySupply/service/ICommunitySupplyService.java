package com.cnfantasia.server.ms.communitySupply.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.communitySupply.service.ICommunitySupplyBaseService;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyEntity;

public interface ICommunitySupplyService extends ICommunitySupplyBaseService {

	List<CommunitySupplyEntity> select_csList_byOmsUser(Map<String, Object> paramMap);


	/**
	 * 根据条件查询(社区商家类别)信息,精确查询
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<CommunitySupplyType> getCommunitySupplyTypeByCondition(Map<String, Object> paramMap);

	/**
	 * 标记推荐，或取消推荐
	 * 
	 * @param csId
	 * @param suggestMark
	 */
	public String suggestMark(String csId, String suggestMark, String pcId, String gbId);

	int getCommunitySupply_byUserId_forCount(Map<String, Object> paramMap);

	List<CommunitySupplyEntity> getCommunitySupplyList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);

}
