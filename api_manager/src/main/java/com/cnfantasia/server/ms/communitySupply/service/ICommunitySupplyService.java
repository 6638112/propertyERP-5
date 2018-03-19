package com.cnfantasia.server.ms.communitySupply.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.communitySupply.service.ICommunitySupplyBaseService;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyBelongEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyBelongViewEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyDetailEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyTmpEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyTmpViewEntity;
import com.cnfantasia.server.ms.communitySupply.entity.PropertySuggestCommsupplyEntity;
import com.cnfantasia.server.ms.communitySupplyCompany.entity.CommunitySupplyCompanyEntity;

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

	List<CommunitySupplyTmpEntity> getCommunitySupplyTmpList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);

	int getCommunitySupplyTmpList_forCount(Map<String, Object> paramMap);

	CommunitySupplyTmpViewEntity getTmpDetailById(String id);

	void auditCSTmpPass(String csTmpId, String omsUserId, String omsUserName, String roleId);

	int getCommunitySupplyBelongList_forCount(Map<String, Object> paramMap);

	List<CommunitySupplyBelongEntity> getCommunitySupplyBelongList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);

	CommunitySupplyBelongViewEntity getBelongDetailById(String id);

	CommunitySupplyDetailEntity getCommunitySupplyDetailById(String id);

	void auditCSBelongPass(String csBelongId, String omsUserId, String omsUserName, String roleId);

	int getPropertySuggestCommsupply_forCount(Map<String, Object> paramMap);

	List<PropertySuggestCommsupplyEntity> getPropertySuggestCommsupply_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);

	PropertySuggestCommsupplyEntity getPropertySuggestCommsupplyDetail(String id);

	CommunitySupplyCompanyEntity getCommunitySupplyCompanyDetail(Map<String, Object> paramMap);
	/**
	 * 验证该单据是否已经被认领
	 * */
	int validateCSBelong_forCount(Map<String, Object> paramMap);
}
