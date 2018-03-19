package com.cnfantasia.server.ms.communitySupplyTmp.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.communitySupplyPic.entity.CommunitySupplyPic;
import com.cnfantasia.server.domainbase.communitySupplyTmp.dao.ICommunitySupplyTmpBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyTmp.entity.CommunitySupplyTmp;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupplyTmp.entity.GroupBuildingHasTCommunitySupplyTmp;
import com.cnfantasia.server.ms.communitySupplyTmp.entity.CommunitySupplyTmpEntity;

public interface ICommunitySupplyTmpDao extends ICommunitySupplyTmpBaseDao {

	public List<CommunitySupplyTmpEntity> getCommunitySupplyTmpList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);
	
	public int getCommunitySupplyTmp_byUserId_forCount(Map<String, Object> paramMap);
	
	/**
	 * 根据id获取临时商家
	 * */
	public CommunitySupplyTmpEntity getCommunitySupplyTmp_byId(BigInteger id);
	
	/**
	 * 根据临时商家id删除服务小区信息
	 * */
	public int delGroupBuildingHasTCommunitySupplyTmp_byId(BigInteger supplyTmpId, BigInteger sysDelUser);
	
	/**
	 * 根据临时商家id删除以前的店铺联系方式
	 * */
	public int delCommunitySupplyTmpContect_byId(BigInteger supplyTmpId, BigInteger sysDelUser);
	
	/**
	 * 根据小区查询物业新增的商家是否存在
	 * */
	public List<String> getGBHasCSIsExists(Map<String, Object> paramMap, boolean isTmp);
	
	/**
	 * 商家编辑审核更新图片
	 * */
	public int updatePicBatchForCSEdit(List<CommunitySupplyPic> picList);
	
	/**
	 * 编辑审核更新临时商家对象
	 * */
	public int updateCSTmpForEdit(CommunitySupplyTmp tmp);
}
