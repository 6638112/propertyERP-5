package com.cnfantasia.server.domainbase.communityExchangeRelation.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communityExchangeRelation.dao.ICommunityExchangeRelationBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityExchangeRelation.entity.CommunityExchangeRelation;

/**
 * 描述:(换物关系表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunityExchangeRelationBaseService implements ICommunityExchangeRelationBaseService{
	
	private ICommunityExchangeRelationBaseDao communityExchangeRelationBaseDao;
	public void setCommunityExchangeRelationBaseDao(ICommunityExchangeRelationBaseDao communityExchangeRelationBaseDao) {
		this.communityExchangeRelationBaseDao = communityExchangeRelationBaseDao;
	}
	/**
	 * 根据条件查询(换物关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityExchangeRelation> getCommunityExchangeRelationByCondition(Map<String,Object> paramMap){
		return communityExchangeRelationBaseDao.selectCommunityExchangeRelationByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(换物关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityExchangeRelation> getCommunityExchangeRelationByConditionDim(Map<String,Object> paramMap){
		return communityExchangeRelationBaseDao.selectCommunityExchangeRelationByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(换物关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityExchangeRelation> getCommunityExchangeRelationByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communityExchangeRelationBaseDao.selectCommunityExchangeRelationByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(换物关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityExchangeRelation> getCommunityExchangeRelationByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communityExchangeRelationBaseDao.selectCommunityExchangeRelationByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(换物关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityExchangeRelation getCommunityExchangeRelationBySeqId(java.math.BigInteger id){
		return communityExchangeRelationBaseDao.selectCommunityExchangeRelationBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(换物关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityExchangeRelationCount(Map<String,Object> paramMap){
		return communityExchangeRelationBaseDao.selectCommunityExchangeRelationCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(换物关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityExchangeRelationCountDim(Map<String,Object> paramMap){
		return communityExchangeRelationBaseDao.selectCommunityExchangeRelationCount(paramMap,true);
	}
	/**
	 * 往(换物关系表)新增一条记录
	 * @param communityExchangeRelation
	 * @return
	 */
	@Override
	public int insertCommunityExchangeRelation(CommunityExchangeRelation communityExchangeRelation){
		return communityExchangeRelationBaseDao.insertCommunityExchangeRelation(communityExchangeRelation);
	}
	/**
	 * 批量新增(换物关系表)
	 * @param communityExchangeRelationList
	 * @return
	 */
	@Override
	public int insertCommunityExchangeRelationBatch(List<CommunityExchangeRelation> communityExchangeRelationList){
		return communityExchangeRelationBaseDao.insertCommunityExchangeRelationBatch(communityExchangeRelationList);
	}
	/**
	 * 更新(换物关系表)信息
	 * @param communityExchangeRelation
	 * @return
	 */
	@Override
	public int updateCommunityExchangeRelation(CommunityExchangeRelation communityExchangeRelation){
		return communityExchangeRelationBaseDao.updateCommunityExchangeRelation(communityExchangeRelation);
	}
	/**
	 * 批量更新(换物关系表)信息
	 * @param communityExchangeRelationList
	 * @return
	 */
	@Override
	public int updateCommunityExchangeRelationBatch(List<CommunityExchangeRelation> communityExchangeRelationList){
		return communityExchangeRelationBaseDao.updateCommunityExchangeRelationBatch(communityExchangeRelationList);
	}
	/**
	 * 根据序列号删除(换物关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityExchangeRelationLogic(java.math.BigInteger id){
		return communityExchangeRelationBaseDao.deleteCommunityExchangeRelationLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(换物关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityExchangeRelationLogicBatch(List<java.math.BigInteger> idList){
		return communityExchangeRelationBaseDao.deleteCommunityExchangeRelationLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(换物关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityExchangeRelation(java.math.BigInteger id){
//		return communityExchangeRelationBaseDao.deleteCommunityExchangeRelation(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(换物关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityExchangeRelationBatch(List<java.math.BigInteger> idList){
//		return communityExchangeRelationBaseDao.deleteCommunityExchangeRelationBatch(idList);
//	}
	
}
