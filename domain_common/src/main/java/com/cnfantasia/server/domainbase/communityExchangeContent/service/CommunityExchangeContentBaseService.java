package com.cnfantasia.server.domainbase.communityExchangeContent.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communityExchangeContent.dao.ICommunityExchangeContentBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityExchangeContent.entity.CommunityExchangeContent;

/**
 * 描述:(换物信息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunityExchangeContentBaseService implements ICommunityExchangeContentBaseService{
	
	private ICommunityExchangeContentBaseDao communityExchangeContentBaseDao;
	public void setCommunityExchangeContentBaseDao(ICommunityExchangeContentBaseDao communityExchangeContentBaseDao) {
		this.communityExchangeContentBaseDao = communityExchangeContentBaseDao;
	}
	/**
	 * 根据条件查询(换物信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityExchangeContent> getCommunityExchangeContentByCondition(Map<String,Object> paramMap){
		return communityExchangeContentBaseDao.selectCommunityExchangeContentByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(换物信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityExchangeContent> getCommunityExchangeContentByConditionDim(Map<String,Object> paramMap){
		return communityExchangeContentBaseDao.selectCommunityExchangeContentByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(换物信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityExchangeContent> getCommunityExchangeContentByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communityExchangeContentBaseDao.selectCommunityExchangeContentByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(换物信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityExchangeContent> getCommunityExchangeContentByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communityExchangeContentBaseDao.selectCommunityExchangeContentByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(换物信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityExchangeContent getCommunityExchangeContentBySeqId(java.math.BigInteger id){
		return communityExchangeContentBaseDao.selectCommunityExchangeContentBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(换物信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityExchangeContentCount(Map<String,Object> paramMap){
		return communityExchangeContentBaseDao.selectCommunityExchangeContentCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(换物信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityExchangeContentCountDim(Map<String,Object> paramMap){
		return communityExchangeContentBaseDao.selectCommunityExchangeContentCount(paramMap,true);
	}
	/**
	 * 往(换物信息表)新增一条记录
	 * @param communityExchangeContent
	 * @return
	 */
	@Override
	public int insertCommunityExchangeContent(CommunityExchangeContent communityExchangeContent){
		return communityExchangeContentBaseDao.insertCommunityExchangeContent(communityExchangeContent);
	}
	/**
	 * 批量新增(换物信息表)
	 * @param communityExchangeContentList
	 * @return
	 */
	@Override
	public int insertCommunityExchangeContentBatch(List<CommunityExchangeContent> communityExchangeContentList){
		return communityExchangeContentBaseDao.insertCommunityExchangeContentBatch(communityExchangeContentList);
	}
	/**
	 * 更新(换物信息表)信息
	 * @param communityExchangeContent
	 * @return
	 */
	@Override
	public int updateCommunityExchangeContent(CommunityExchangeContent communityExchangeContent){
		return communityExchangeContentBaseDao.updateCommunityExchangeContent(communityExchangeContent);
	}
	/**
	 * 批量更新(换物信息表)信息
	 * @param communityExchangeContentList
	 * @return
	 */
	@Override
	public int updateCommunityExchangeContentBatch(List<CommunityExchangeContent> communityExchangeContentList){
		return communityExchangeContentBaseDao.updateCommunityExchangeContentBatch(communityExchangeContentList);
	}
	/**
	 * 根据序列号删除(换物信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityExchangeContentLogic(java.math.BigInteger id){
		return communityExchangeContentBaseDao.deleteCommunityExchangeContentLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(换物信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityExchangeContentLogicBatch(List<java.math.BigInteger> idList){
		return communityExchangeContentBaseDao.deleteCommunityExchangeContentLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(换物信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityExchangeContent(java.math.BigInteger id){
//		return communityExchangeContentBaseDao.deleteCommunityExchangeContent(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(换物信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityExchangeContentBatch(List<java.math.BigInteger> idList){
//		return communityExchangeContentBaseDao.deleteCommunityExchangeContentBatch(idList);
//	}
	
}
