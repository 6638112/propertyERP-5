package com.cnfantasia.server.domainbase.communityForumContent.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communityForumContent.dao.ICommunityForumContentBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityForumContent.entity.CommunityForumContent;

/**
 * 描述:(社区论坛的帖子内容) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunityForumContentBaseService implements ICommunityForumContentBaseService{
	
	private ICommunityForumContentBaseDao communityForumContentBaseDao;
	public void setCommunityForumContentBaseDao(ICommunityForumContentBaseDao communityForumContentBaseDao) {
		this.communityForumContentBaseDao = communityForumContentBaseDao;
	}
	/**
	 * 根据条件查询(社区论坛的帖子内容)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityForumContent> getCommunityForumContentByCondition(Map<String,Object> paramMap){
		return communityForumContentBaseDao.selectCommunityForumContentByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(社区论坛的帖子内容)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityForumContent> getCommunityForumContentByConditionDim(Map<String,Object> paramMap){
		return communityForumContentBaseDao.selectCommunityForumContentByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(社区论坛的帖子内容)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityForumContent> getCommunityForumContentByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communityForumContentBaseDao.selectCommunityForumContentByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(社区论坛的帖子内容)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityForumContent> getCommunityForumContentByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communityForumContentBaseDao.selectCommunityForumContentByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(社区论坛的帖子内容)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityForumContent getCommunityForumContentBySeqId(java.math.BigInteger id){
		return communityForumContentBaseDao.selectCommunityForumContentBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(社区论坛的帖子内容)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityForumContentCount(Map<String,Object> paramMap){
		return communityForumContentBaseDao.selectCommunityForumContentCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(社区论坛的帖子内容)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityForumContentCountDim(Map<String,Object> paramMap){
		return communityForumContentBaseDao.selectCommunityForumContentCount(paramMap,true);
	}
	/**
	 * 往(社区论坛的帖子内容)新增一条记录
	 * @param communityForumContent
	 * @return
	 */
	@Override
	public int insertCommunityForumContent(CommunityForumContent communityForumContent){
		return communityForumContentBaseDao.insertCommunityForumContent(communityForumContent);
	}
	/**
	 * 批量新增(社区论坛的帖子内容)
	 * @param communityForumContentList
	 * @return
	 */
	@Override
	public int insertCommunityForumContentBatch(List<CommunityForumContent> communityForumContentList){
		return communityForumContentBaseDao.insertCommunityForumContentBatch(communityForumContentList);
	}
	/**
	 * 更新(社区论坛的帖子内容)信息
	 * @param communityForumContent
	 * @return
	 */
	@Override
	public int updateCommunityForumContent(CommunityForumContent communityForumContent){
		return communityForumContentBaseDao.updateCommunityForumContent(communityForumContent);
	}
	/**
	 * 批量更新(社区论坛的帖子内容)信息
	 * @param communityForumContentList
	 * @return
	 */
	@Override
	public int updateCommunityForumContentBatch(List<CommunityForumContent> communityForumContentList){
		return communityForumContentBaseDao.updateCommunityForumContentBatch(communityForumContentList);
	}
	/**
	 * 根据序列号删除(社区论坛的帖子内容)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityForumContentLogic(java.math.BigInteger id){
		return communityForumContentBaseDao.deleteCommunityForumContentLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(社区论坛的帖子内容)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityForumContentLogicBatch(List<java.math.BigInteger> idList){
		return communityForumContentBaseDao.deleteCommunityForumContentLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(社区论坛的帖子内容)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityForumContent(java.math.BigInteger id){
//		return communityForumContentBaseDao.deleteCommunityForumContent(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区论坛的帖子内容)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityForumContentBatch(List<java.math.BigInteger> idList){
//		return communityForumContentBaseDao.deleteCommunityForumContentBatch(idList);
//	}
	
}
