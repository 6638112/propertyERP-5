package com.cnfantasia.server.domainbase.communityForumPic.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communityForumPic.dao.ICommunityForumPicBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityForumPic.entity.CommunityForumPic;

/**
 * 描述:(社区论坛的帖子图片) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunityForumPicBaseService implements ICommunityForumPicBaseService{
	
	private ICommunityForumPicBaseDao communityForumPicBaseDao;
	public void setCommunityForumPicBaseDao(ICommunityForumPicBaseDao communityForumPicBaseDao) {
		this.communityForumPicBaseDao = communityForumPicBaseDao;
	}
	/**
	 * 根据条件查询(社区论坛的帖子图片)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityForumPic> getCommunityForumPicByCondition(Map<String,Object> paramMap){
		return communityForumPicBaseDao.selectCommunityForumPicByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(社区论坛的帖子图片)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityForumPic> getCommunityForumPicByConditionDim(Map<String,Object> paramMap){
		return communityForumPicBaseDao.selectCommunityForumPicByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(社区论坛的帖子图片)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityForumPic> getCommunityForumPicByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communityForumPicBaseDao.selectCommunityForumPicByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(社区论坛的帖子图片)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityForumPic> getCommunityForumPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communityForumPicBaseDao.selectCommunityForumPicByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(社区论坛的帖子图片)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityForumPic getCommunityForumPicBySeqId(java.math.BigInteger id){
		return communityForumPicBaseDao.selectCommunityForumPicBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(社区论坛的帖子图片)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityForumPicCount(Map<String,Object> paramMap){
		return communityForumPicBaseDao.selectCommunityForumPicCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(社区论坛的帖子图片)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityForumPicCountDim(Map<String,Object> paramMap){
		return communityForumPicBaseDao.selectCommunityForumPicCount(paramMap,true);
	}
	/**
	 * 往(社区论坛的帖子图片)新增一条记录
	 * @param communityForumPic
	 * @return
	 */
	@Override
	public int insertCommunityForumPic(CommunityForumPic communityForumPic){
		return communityForumPicBaseDao.insertCommunityForumPic(communityForumPic);
	}
	/**
	 * 批量新增(社区论坛的帖子图片)
	 * @param communityForumPicList
	 * @return
	 */
	@Override
	public int insertCommunityForumPicBatch(List<CommunityForumPic> communityForumPicList){
		return communityForumPicBaseDao.insertCommunityForumPicBatch(communityForumPicList);
	}
	/**
	 * 更新(社区论坛的帖子图片)信息
	 * @param communityForumPic
	 * @return
	 */
	@Override
	public int updateCommunityForumPic(CommunityForumPic communityForumPic){
		return communityForumPicBaseDao.updateCommunityForumPic(communityForumPic);
	}
	/**
	 * 批量更新(社区论坛的帖子图片)信息
	 * @param communityForumPicList
	 * @return
	 */
	@Override
	public int updateCommunityForumPicBatch(List<CommunityForumPic> communityForumPicList){
		return communityForumPicBaseDao.updateCommunityForumPicBatch(communityForumPicList);
	}
	/**
	 * 根据序列号删除(社区论坛的帖子图片)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityForumPicLogic(java.math.BigInteger id){
		return communityForumPicBaseDao.deleteCommunityForumPicLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(社区论坛的帖子图片)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityForumPicLogicBatch(List<java.math.BigInteger> idList){
		return communityForumPicBaseDao.deleteCommunityForumPicLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(社区论坛的帖子图片)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityForumPic(java.math.BigInteger id){
//		return communityForumPicBaseDao.deleteCommunityForumPic(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区论坛的帖子图片)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityForumPicBatch(List<java.math.BigInteger> idList){
//		return communityForumPicBaseDao.deleteCommunityForumPicBatch(idList);
//	}
	
}
