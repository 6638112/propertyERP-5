package com.cnfantasia.server.ms.communitySupplyCompany.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.communitySupplyCompany.dao.ICommunitySupplyCompanyBaseDao;
import com.cnfantasia.server.ms.communitySupplyCompany.entity.CommunitySupplyCompanyEntity;
import com.cnfantasia.server.ms.communitySupplyCompany.entity.CommunitySupplyEditEntity;

public interface ICommunitySupplyCompanyDao extends ICommunitySupplyCompanyBaseDao {
	/**
	 * 查询商户公司
	 * */
	public List<CommunitySupplyCompanyEntity> getCommunitySupplyCompanyList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);
	/**
	 * 查询商户公司数量
	 * */
	public int getCommunitySupplyCompany_byUserId_forCount(Map<String, Object> paramMap);
	
	/**
	 * 查询商户公司所属店铺编辑
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
}
