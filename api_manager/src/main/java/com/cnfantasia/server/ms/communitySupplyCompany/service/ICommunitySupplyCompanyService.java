package com.cnfantasia.server.ms.communitySupplyCompany.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.communitySupplyCompany.service.ICommunitySupplyCompanyBaseService;
import com.cnfantasia.server.ms.communitySupplyCompany.entity.CommunitySupplyCompanyEntity;
import com.cnfantasia.server.ms.communitySupplyCompany.entity.CommunitySupplyEditEntity;

public interface ICommunitySupplyCompanyService extends ICommunitySupplyCompanyBaseService {

	/**
	 * 根据条件查询商户公司信息,分页查询
	 * @param paramMap
	 * @return
	 */
	List<CommunitySupplyCompanyEntity> getCommunitySupplyTmpList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);
	
	/**
	 * 根据条件查询商户公司数量
	 * @param paramMap
	 * @return
	 */
	int getCommunitySupplyCompany_byUserId_forCount(Map<String, Object> paramMap);
	
	/**
	 * 查询商户公司所属店铺编辑,分页查询
	 * */
	public List<CommunitySupplyEditEntity> getCommunitySupplyEditEntityList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);
	/**
	 * 查询商户公司所属店铺编辑数量
	 * */
	public int getCommunitySupplyEditEntityList_byUserId_forCount(Map<String, Object> paramMap);
	/**
	 * 查询商户公司所属店铺详情
	 * */
	public CommunitySupplyEditEntity getCommunitySupplyEditEntity_byId(BigInteger id);
	
	/**
	 * 物业公司保存社区商家临时信息
	 * @param supplyTmpId
	 * @param supplyType
	 * @param supplyId
	 * @param supplyName
	 * @param contectPhone
	 * @param addressBlockId
	 * @param addressDetail
	 * @param contactImgUrl
	 * @return
	 */
	public int saveCommunitySupplyEditTmp(String supplyTmpId, String supplyType, String supplyId, String supplyName, String[] contectPhones,  
			String addressBlockId, String addressDetail, List<String> contactImgUrls, String delImgIds, String delContectIds);
	
	/**
	 * 审核通过，更新店铺的信息
	 * */
	public int auditCSEditPass(String tmpId, String supplyId);
}
