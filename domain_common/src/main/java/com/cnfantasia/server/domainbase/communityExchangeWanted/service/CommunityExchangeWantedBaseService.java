package com.cnfantasia.server.domainbase.communityExchangeWanted.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communityExchangeWanted.dao.ICommunityExchangeWantedBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityExchangeWanted.entity.CommunityExchangeWanted;

/**
 * 描述:(换一换之跪求换) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunityExchangeWantedBaseService implements ICommunityExchangeWantedBaseService{
	
	private ICommunityExchangeWantedBaseDao communityExchangeWantedBaseDao;
	public void setCommunityExchangeWantedBaseDao(ICommunityExchangeWantedBaseDao communityExchangeWantedBaseDao) {
		this.communityExchangeWantedBaseDao = communityExchangeWantedBaseDao;
	}
	/**
	 * 根据条件查询(换一换之跪求换)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityExchangeWanted> getCommunityExchangeWantedByCondition(Map<String,Object> paramMap){
		return communityExchangeWantedBaseDao.selectCommunityExchangeWantedByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(换一换之跪求换)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityExchangeWanted> getCommunityExchangeWantedByConditionDim(Map<String,Object> paramMap){
		return communityExchangeWantedBaseDao.selectCommunityExchangeWantedByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(换一换之跪求换)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityExchangeWanted> getCommunityExchangeWantedByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communityExchangeWantedBaseDao.selectCommunityExchangeWantedByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(换一换之跪求换)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityExchangeWanted> getCommunityExchangeWantedByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communityExchangeWantedBaseDao.selectCommunityExchangeWantedByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(换一换之跪求换)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityExchangeWanted getCommunityExchangeWantedBySeqId(java.math.BigInteger id){
		return communityExchangeWantedBaseDao.selectCommunityExchangeWantedBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(换一换之跪求换)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityExchangeWantedCount(Map<String,Object> paramMap){
		return communityExchangeWantedBaseDao.selectCommunityExchangeWantedCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(换一换之跪求换)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityExchangeWantedCountDim(Map<String,Object> paramMap){
		return communityExchangeWantedBaseDao.selectCommunityExchangeWantedCount(paramMap,true);
	}
	/**
	 * 往(换一换之跪求换)新增一条记录
	 * @param communityExchangeWanted
	 * @return
	 */
	@Override
	public int insertCommunityExchangeWanted(CommunityExchangeWanted communityExchangeWanted){
		return communityExchangeWantedBaseDao.insertCommunityExchangeWanted(communityExchangeWanted);
	}
	/**
	 * 批量新增(换一换之跪求换)
	 * @param communityExchangeWantedList
	 * @return
	 */
	@Override
	public int insertCommunityExchangeWantedBatch(List<CommunityExchangeWanted> communityExchangeWantedList){
		return communityExchangeWantedBaseDao.insertCommunityExchangeWantedBatch(communityExchangeWantedList);
	}
	/**
	 * 更新(换一换之跪求换)信息
	 * @param communityExchangeWanted
	 * @return
	 */
	@Override
	public int updateCommunityExchangeWanted(CommunityExchangeWanted communityExchangeWanted){
		return communityExchangeWantedBaseDao.updateCommunityExchangeWanted(communityExchangeWanted);
	}
	/**
	 * 批量更新(换一换之跪求换)信息
	 * @param communityExchangeWantedList
	 * @return
	 */
	@Override
	public int updateCommunityExchangeWantedBatch(List<CommunityExchangeWanted> communityExchangeWantedList){
		return communityExchangeWantedBaseDao.updateCommunityExchangeWantedBatch(communityExchangeWantedList);
	}
	/**
	 * 根据序列号删除(换一换之跪求换)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityExchangeWantedLogic(java.math.BigInteger id){
		return communityExchangeWantedBaseDao.deleteCommunityExchangeWantedLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(换一换之跪求换)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityExchangeWantedLogicBatch(List<java.math.BigInteger> idList){
		return communityExchangeWantedBaseDao.deleteCommunityExchangeWantedLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(换一换之跪求换)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityExchangeWanted(java.math.BigInteger id){
//		return communityExchangeWantedBaseDao.deleteCommunityExchangeWanted(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(换一换之跪求换)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityExchangeWantedBatch(List<java.math.BigInteger> idList){
//		return communityExchangeWantedBaseDao.deleteCommunityExchangeWantedBatch(idList);
//	}
	
}
