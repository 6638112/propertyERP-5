package com.cnfantasia.server.domainbase.communityForumType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communityForumType.dao.ICommunityForumTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityForumType.entity.CommunityForumType;

/**
 * 描述:(社区论坛的帖子类别) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunityForumTypeBaseService implements ICommunityForumTypeBaseService{
	
	private ICommunityForumTypeBaseDao communityForumTypeBaseDao;
	public void setCommunityForumTypeBaseDao(ICommunityForumTypeBaseDao communityForumTypeBaseDao) {
		this.communityForumTypeBaseDao = communityForumTypeBaseDao;
	}
	/**
	 * 根据条件查询(社区论坛的帖子类别)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityForumType> getCommunityForumTypeByCondition(Map<String,Object> paramMap){
		return communityForumTypeBaseDao.selectCommunityForumTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(社区论坛的帖子类别)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityForumType> getCommunityForumTypeByConditionDim(Map<String,Object> paramMap){
		return communityForumTypeBaseDao.selectCommunityForumTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(社区论坛的帖子类别)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityForumType> getCommunityForumTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communityForumTypeBaseDao.selectCommunityForumTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(社区论坛的帖子类别)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityForumType> getCommunityForumTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communityForumTypeBaseDao.selectCommunityForumTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(社区论坛的帖子类别)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityForumType getCommunityForumTypeBySeqId(java.math.BigInteger id){
		return communityForumTypeBaseDao.selectCommunityForumTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(社区论坛的帖子类别)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityForumTypeCount(Map<String,Object> paramMap){
		return communityForumTypeBaseDao.selectCommunityForumTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(社区论坛的帖子类别)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityForumTypeCountDim(Map<String,Object> paramMap){
		return communityForumTypeBaseDao.selectCommunityForumTypeCount(paramMap,true);
	}
	/**
	 * 往(社区论坛的帖子类别)新增一条记录
	 * @param communityForumType
	 * @return
	 */
	@Override
	public int insertCommunityForumType(CommunityForumType communityForumType){
		return communityForumTypeBaseDao.insertCommunityForumType(communityForumType);
	}
	/**
	 * 批量新增(社区论坛的帖子类别)
	 * @param communityForumTypeList
	 * @return
	 */
	@Override
	public int insertCommunityForumTypeBatch(List<CommunityForumType> communityForumTypeList){
		return communityForumTypeBaseDao.insertCommunityForumTypeBatch(communityForumTypeList);
	}
	/**
	 * 更新(社区论坛的帖子类别)信息
	 * @param communityForumType
	 * @return
	 */
	@Override
	public int updateCommunityForumType(CommunityForumType communityForumType){
		return communityForumTypeBaseDao.updateCommunityForumType(communityForumType);
	}
	/**
	 * 批量更新(社区论坛的帖子类别)信息
	 * @param communityForumTypeList
	 * @return
	 */
	@Override
	public int updateCommunityForumTypeBatch(List<CommunityForumType> communityForumTypeList){
		return communityForumTypeBaseDao.updateCommunityForumTypeBatch(communityForumTypeList);
	}
	/**
	 * 根据序列号删除(社区论坛的帖子类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityForumTypeLogic(java.math.BigInteger id){
		return communityForumTypeBaseDao.deleteCommunityForumTypeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(社区论坛的帖子类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityForumTypeLogicBatch(List<java.math.BigInteger> idList){
		return communityForumTypeBaseDao.deleteCommunityForumTypeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(社区论坛的帖子类别)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityForumType(java.math.BigInteger id){
//		return communityForumTypeBaseDao.deleteCommunityForumType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区论坛的帖子类别)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityForumTypeBatch(List<java.math.BigInteger> idList){
//		return communityForumTypeBaseDao.deleteCommunityForumTypeBatch(idList);
//	}
	
}
