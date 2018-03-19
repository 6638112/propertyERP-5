package com.cnfantasia.server.domainbase.communitySupply.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communitySupply.dao.ICommunitySupplyBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;

/**
 * 描述:(社区商家信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunitySupplyBaseService implements ICommunitySupplyBaseService{
	
	private ICommunitySupplyBaseDao communitySupplyBaseDao;
	public void setCommunitySupplyBaseDao(ICommunitySupplyBaseDao communitySupplyBaseDao) {
		this.communitySupplyBaseDao = communitySupplyBaseDao;
	}
	/**
	 * 根据条件查询(社区商家信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupply> getCommunitySupplyByCondition(Map<String,Object> paramMap){
		return communitySupplyBaseDao.selectCommunitySupplyByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(社区商家信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupply> getCommunitySupplyByConditionDim(Map<String,Object> paramMap){
		return communitySupplyBaseDao.selectCommunitySupplyByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(社区商家信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupply> getCommunitySupplyByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyBaseDao.selectCommunitySupplyByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(社区商家信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupply> getCommunitySupplyByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyBaseDao.selectCommunitySupplyByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(社区商家信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupply getCommunitySupplyBySeqId(java.math.BigInteger id){
		return communitySupplyBaseDao.selectCommunitySupplyBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(社区商家信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyCount(Map<String,Object> paramMap){
		return communitySupplyBaseDao.selectCommunitySupplyCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(社区商家信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyCountDim(Map<String,Object> paramMap){
		return communitySupplyBaseDao.selectCommunitySupplyCount(paramMap,true);
	}
	/**
	 * 往(社区商家信息)新增一条记录
	 * @param communitySupply
	 * @return
	 */
	@Override
	public int insertCommunitySupply(CommunitySupply communitySupply){
		return communitySupplyBaseDao.insertCommunitySupply(communitySupply);
	}
	/**
	 * 批量新增(社区商家信息)
	 * @param communitySupplyList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyBatch(List<CommunitySupply> communitySupplyList){
		return communitySupplyBaseDao.insertCommunitySupplyBatch(communitySupplyList);
	}
	/**
	 * 更新(社区商家信息)信息
	 * @param communitySupply
	 * @return
	 */
	@Override
	public int updateCommunitySupply(CommunitySupply communitySupply){
		return communitySupplyBaseDao.updateCommunitySupply(communitySupply);
	}
	/**
	 * 批量更新(社区商家信息)信息
	 * @param communitySupplyList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyBatch(List<CommunitySupply> communitySupplyList){
		return communitySupplyBaseDao.updateCommunitySupplyBatch(communitySupplyList);
	}
	/**
	 * 根据序列号删除(社区商家信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyLogic(java.math.BigInteger id){
		return communitySupplyBaseDao.deleteCommunitySupplyLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(社区商家信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyLogicBatch(List<java.math.BigInteger> idList){
		return communitySupplyBaseDao.deleteCommunitySupplyLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(社区商家信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupply(java.math.BigInteger id){
//		return communitySupplyBaseDao.deleteCommunitySupply(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区商家信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyBatch(List<java.math.BigInteger> idList){
//		return communitySupplyBaseDao.deleteCommunitySupplyBatch(idList);
//	}
	
}
