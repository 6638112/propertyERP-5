package com.cnfantasia.server.domainbase.communityAds.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityAds.entity.CommunityAds;

/**
 * 描述:(街坊广告信息表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityAdsBaseDao {
	/**
	 * 根据条件查询(街坊广告信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityAds> selectCommunityAdsByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(街坊广告信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityAds> selectCommunityAdsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(街坊广告信息表)信息
	 * @param id
	 * @return
	 */
	public CommunityAds selectCommunityAdsBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(街坊广告信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunityAdsCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(街坊广告信息表)新增一条记录
	 * @param communityAds
	 * @return
	 */
	public int insertCommunityAds(CommunityAds communityAds);
	
	/**
	 * 批量新增(街坊广告信息表)信息
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
	 * 根据Id 批量删除(街坊广告信息表)信息_逻辑删除
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
