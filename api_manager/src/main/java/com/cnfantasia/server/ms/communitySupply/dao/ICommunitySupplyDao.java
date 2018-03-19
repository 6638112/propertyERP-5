package com.cnfantasia.server.ms.communitySupply.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.communitySupply.dao.ICommunitySupplyBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyBelongEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyBelongViewEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyDetailEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyTmpEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyTmpViewEntity;
import com.cnfantasia.server.ms.communitySupply.entity.PropertySuggestCommsupplyEntity;
import com.cnfantasia.server.ms.communitySupplyCompany.entity.CommunitySupplyCompanyEntity;

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

	/**
	 * 为管理员找出所有新增商家
	 */
	public List<CommunitySupplyTmpEntity> getCommunitySupplyTmpList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);

	public int getCommunitySupplyTmpList_forCount(Map<String, Object> paramMap);

	public CommunitySupplyTmpViewEntity getTmpDetailById(String id);

	public int getCommunitySupplyBelongList_forCount(Map<String, Object> paramMap);

	public List<CommunitySupplyBelongEntity> getCommunitySupplyBelongList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);

	public CommunitySupplyBelongViewEntity getBelongDetailById(String id);

	public int getPropertySuggestCommsupply_forCount(Map<String, Object> paramMap);

	public List<PropertySuggestCommsupplyEntity> getPropertySuggestCommsupply_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);

	public CommunitySupplyDetailEntity getCommunitySupplyDetailById(String id);

	public PropertySuggestCommsupplyEntity getPropertySuggestCommsupplyDetail(String id);

	public CommunitySupplyCompanyEntity getCommunitySupplyCompanyDetail(Map<String, Object> paramMap);
	
	/**
	 * 验证该单据是否已经被认领
	 * */
	int validateCSBelong_forCount(Map<String, Object> paramMap);
}
