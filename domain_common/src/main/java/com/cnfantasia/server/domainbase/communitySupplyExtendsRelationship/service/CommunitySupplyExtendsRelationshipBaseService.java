package com.cnfantasia.server.domainbase.communitySupplyExtendsRelationship.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communitySupplyExtendsRelationship.dao.ICommunitySupplyExtendsRelationshipBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyExtendsRelationship.entity.CommunitySupplyExtendsRelationship;

/**
 * 描述:(社区商家拓展对应信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunitySupplyExtendsRelationshipBaseService implements ICommunitySupplyExtendsRelationshipBaseService{
	
	private ICommunitySupplyExtendsRelationshipBaseDao communitySupplyExtendsRelationshipBaseDao;
	public void setCommunitySupplyExtendsRelationshipBaseDao(ICommunitySupplyExtendsRelationshipBaseDao communitySupplyExtendsRelationshipBaseDao) {
		this.communitySupplyExtendsRelationshipBaseDao = communitySupplyExtendsRelationshipBaseDao;
	}
	/**
	 * 根据条件查询(社区商家拓展对应信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyExtendsRelationship> getCommunitySupplyExtendsRelationshipByCondition(Map<String,Object> paramMap){
		return communitySupplyExtendsRelationshipBaseDao.selectCommunitySupplyExtendsRelationshipByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(社区商家拓展对应信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyExtendsRelationship> getCommunitySupplyExtendsRelationshipByConditionDim(Map<String,Object> paramMap){
		return communitySupplyExtendsRelationshipBaseDao.selectCommunitySupplyExtendsRelationshipByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(社区商家拓展对应信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyExtendsRelationship> getCommunitySupplyExtendsRelationshipByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyExtendsRelationshipBaseDao.selectCommunitySupplyExtendsRelationshipByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(社区商家拓展对应信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyExtendsRelationship> getCommunitySupplyExtendsRelationshipByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyExtendsRelationshipBaseDao.selectCommunitySupplyExtendsRelationshipByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(社区商家拓展对应信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyExtendsRelationship getCommunitySupplyExtendsRelationshipBySeqId(java.math.BigInteger id){
		return communitySupplyExtendsRelationshipBaseDao.selectCommunitySupplyExtendsRelationshipBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(社区商家拓展对应信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyExtendsRelationshipCount(Map<String,Object> paramMap){
		return communitySupplyExtendsRelationshipBaseDao.selectCommunitySupplyExtendsRelationshipCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(社区商家拓展对应信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyExtendsRelationshipCountDim(Map<String,Object> paramMap){
		return communitySupplyExtendsRelationshipBaseDao.selectCommunitySupplyExtendsRelationshipCount(paramMap,true);
	}
	/**
	 * 往(社区商家拓展对应信息)新增一条记录
	 * @param communitySupplyExtendsRelationship
	 * @return
	 */
	@Override
	public int insertCommunitySupplyExtendsRelationship(CommunitySupplyExtendsRelationship communitySupplyExtendsRelationship){
		return communitySupplyExtendsRelationshipBaseDao.insertCommunitySupplyExtendsRelationship(communitySupplyExtendsRelationship);
	}
	/**
	 * 批量新增(社区商家拓展对应信息)
	 * @param communitySupplyExtendsRelationshipList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyExtendsRelationshipBatch(List<CommunitySupplyExtendsRelationship> communitySupplyExtendsRelationshipList){
		return communitySupplyExtendsRelationshipBaseDao.insertCommunitySupplyExtendsRelationshipBatch(communitySupplyExtendsRelationshipList);
	}
	/**
	 * 更新(社区商家拓展对应信息)信息
	 * @param communitySupplyExtendsRelationship
	 * @return
	 */
	@Override
	public int updateCommunitySupplyExtendsRelationship(CommunitySupplyExtendsRelationship communitySupplyExtendsRelationship){
		return communitySupplyExtendsRelationshipBaseDao.updateCommunitySupplyExtendsRelationship(communitySupplyExtendsRelationship);
	}
	/**
	 * 批量更新(社区商家拓展对应信息)信息
	 * @param communitySupplyExtendsRelationshipList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyExtendsRelationshipBatch(List<CommunitySupplyExtendsRelationship> communitySupplyExtendsRelationshipList){
		return communitySupplyExtendsRelationshipBaseDao.updateCommunitySupplyExtendsRelationshipBatch(communitySupplyExtendsRelationshipList);
	}
	/**
	 * 根据序列号删除(社区商家拓展对应信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyExtendsRelationshipLogic(java.math.BigInteger id){
		return communitySupplyExtendsRelationshipBaseDao.deleteCommunitySupplyExtendsRelationshipLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(社区商家拓展对应信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyExtendsRelationshipLogicBatch(List<java.math.BigInteger> idList){
		return communitySupplyExtendsRelationshipBaseDao.deleteCommunitySupplyExtendsRelationshipLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(社区商家拓展对应信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyExtendsRelationship(java.math.BigInteger id){
//		return communitySupplyExtendsRelationshipBaseDao.deleteCommunitySupplyExtendsRelationship(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区商家拓展对应信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyExtendsRelationshipBatch(List<java.math.BigInteger> idList){
//		return communitySupplyExtendsRelationshipBaseDao.deleteCommunitySupplyExtendsRelationshipBatch(idList);
//	}
	
}
