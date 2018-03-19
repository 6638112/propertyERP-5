package com.cnfantasia.server.domainbase.communityAds.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communityAds.dao.ICommunityAdsBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityAds.entity.CommunityAds;

/**
 * 描述:(街坊广告信息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunityAdsBaseService implements ICommunityAdsBaseService{
	
	private ICommunityAdsBaseDao communityAdsBaseDao;
	public void setCommunityAdsBaseDao(ICommunityAdsBaseDao communityAdsBaseDao) {
		this.communityAdsBaseDao = communityAdsBaseDao;
	}
	/**
	 * 根据条件查询(街坊广告信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityAds> getCommunityAdsByCondition(Map<String,Object> paramMap){
		return communityAdsBaseDao.selectCommunityAdsByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(街坊广告信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityAds> getCommunityAdsByConditionDim(Map<String,Object> paramMap){
		return communityAdsBaseDao.selectCommunityAdsByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(街坊广告信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityAds> getCommunityAdsByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communityAdsBaseDao.selectCommunityAdsByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(街坊广告信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityAds> getCommunityAdsByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communityAdsBaseDao.selectCommunityAdsByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(街坊广告信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityAds getCommunityAdsBySeqId(java.math.BigInteger id){
		return communityAdsBaseDao.selectCommunityAdsBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(街坊广告信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityAdsCount(Map<String,Object> paramMap){
		return communityAdsBaseDao.selectCommunityAdsCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(街坊广告信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityAdsCountDim(Map<String,Object> paramMap){
		return communityAdsBaseDao.selectCommunityAdsCount(paramMap,true);
	}
	/**
	 * 往(街坊广告信息表)新增一条记录
	 * @param communityAds
	 * @return
	 */
	@Override
	public int insertCommunityAds(CommunityAds communityAds){
		return communityAdsBaseDao.insertCommunityAds(communityAds);
	}
	/**
	 * 批量新增(街坊广告信息表)
	 * @param communityAdsList
	 * @return
	 */
	@Override
	public int insertCommunityAdsBatch(List<CommunityAds> communityAdsList){
		return communityAdsBaseDao.insertCommunityAdsBatch(communityAdsList);
	}
	/**
	 * 更新(街坊广告信息表)信息
	 * @param communityAds
	 * @return
	 */
	@Override
	public int updateCommunityAds(CommunityAds communityAds){
		return communityAdsBaseDao.updateCommunityAds(communityAds);
	}
	/**
	 * 批量更新(街坊广告信息表)信息
	 * @param communityAdsList
	 * @return
	 */
	@Override
	public int updateCommunityAdsBatch(List<CommunityAds> communityAdsList){
		return communityAdsBaseDao.updateCommunityAdsBatch(communityAdsList);
	}
	/**
	 * 根据序列号删除(街坊广告信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityAdsLogic(java.math.BigInteger id){
		return communityAdsBaseDao.deleteCommunityAdsLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(街坊广告信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityAdsLogicBatch(List<java.math.BigInteger> idList){
		return communityAdsBaseDao.deleteCommunityAdsLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(街坊广告信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityAds(java.math.BigInteger id){
//		return communityAdsBaseDao.deleteCommunityAds(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(街坊广告信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityAdsBatch(List<java.math.BigInteger> idList){
//		return communityAdsBaseDao.deleteCommunityAdsBatch(idList);
//	}
	
}
