package com.cnfantasia.server.ms.communitySupplyTmp.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.communitySupplyTmp.service.ICommunitySupplyTmpBaseService;
import com.cnfantasia.server.ms.communitySupplyTmp.entity.CommunitySupplyTmpEntity;

public interface ICommunitySupplyTmpService extends ICommunitySupplyTmpBaseService {

	/**
	 * 物业公司保存社区商家临时信息
	 * @param supplyTmpId
	 * @param supplyType
	 * @param supplyName
	 * @param companyName
	 * @param companyPhone
	 * @param addressBlockId
	 * @param addressDetail
	 * @param gbListId
	 * @param miniPicId
	 * @param bigPicId
	 * @param contactImgMiniUrl
	 * @param contactImgUrl
	 * @return
	 */
	public int saveCommunitySupplyTmp(String supplyTmpId, String supplyType, String supplyName, String companyName, String companyPhone,
			String[] contectPhones, String addressBlockId, String addressDetail, String[] serviceGroupBuildingIds, 
			String miniPicId, String bigPicId, List<String> contactImgUrls, String delImgIds, String delContectIds);

	/**
	 * 根据条件查询(社区商家临时)信息,分页查询
	 * @param paramMap
	 * @return
	 */
	List<CommunitySupplyTmpEntity> getCommunitySupplyTmpList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);
	
	/**
	 * 根据条件查询(社区商家临时)数量
	 * @param paramMap
	 * @return
	 */
	int getCommunitySupplyTmp_byUserId_forCount(Map<String, Object> paramMap);
	
	/**
	 * 根据id获取临时商家
	 * */
	CommunitySupplyTmpEntity getCommunitySupplyTmp_byId(BigInteger id);
	
	/**
	 * 根据临时商家ID删除商家信息
	 * */
	public int delCommunitySupplyTmp(BigInteger supplyTmpId);
	
	/**
	 * 根据小区查询物业新增的商家是否存在
	 * */
	public List<String> getGBHasCSIsExists(Map<String, Object> paramMap, boolean isTmp);
}
