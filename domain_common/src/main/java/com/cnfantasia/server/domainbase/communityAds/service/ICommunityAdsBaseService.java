package com.cnfantasia.server.domainbase.communityAds.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communityAds.entity.CommunityAds;

/**
 * 描述:(街坊广告信息表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityAdsBaseService {
	
	/**
	 * 根据条件查询(街坊广告信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityAds> getCommunityAdsByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(街坊广告信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityAds> getCommunityAdsByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(街坊广告信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityAds> getCommunityAdsByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(街坊广告信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityAds> getCommunityAdsByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(街坊广告信息表)信息
	 * @param id
	 * @return
	 */
	public CommunityAds getCommunityAdsBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(街坊广告信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityAdsCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(街坊广告信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityAdsCountDim(Map<String,Object> paramMap);
	/**
	 * 往(街坊广告信息表)新增一条记录
	 * @param communityAds
	 * @return
	 */
	public int insertCommunityAds(CommunityAds communityAds);
	/**
	 * 批量新增(街坊广告信息表)
	 * @param communityAdsList
	 * @return
	 */
	public int insertCommunityAdsBatch(List<CommunityAds> communityAdsList);
	/**
	 * 更新(街坊广告信息表)信息
	 * @param communityAds
	 * @return
	 */
	public int updateCommunityAds(CommunityAds communityAds);
	/**
	 * 批量更新(街坊广告信息表)信息
	 * @param communityAdsList
	 * @return
	 */
	public int updateCommunityAdsBatch(List<CommunityAds> communityAdsList);
	/**
	 * 根据序列号删除(街坊广告信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunityAdsLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(街坊广告信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunityAdsLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(街坊广告信息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunityAds(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(街坊广告信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunityAdsBatch(List<java.math.BigInteger> idList);
	
}
