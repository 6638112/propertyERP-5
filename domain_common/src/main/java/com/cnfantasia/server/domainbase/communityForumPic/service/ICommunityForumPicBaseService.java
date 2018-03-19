package com.cnfantasia.server.domainbase.communityForumPic.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communityForumPic.entity.CommunityForumPic;

/**
 * 描述:(社区论坛的帖子图片) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityForumPicBaseService {
	
	/**
	 * 根据条件查询(社区论坛的帖子图片)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityForumPic> getCommunityForumPicByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(社区论坛的帖子图片)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityForumPic> getCommunityForumPicByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(社区论坛的帖子图片)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityForumPic> getCommunityForumPicByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(社区论坛的帖子图片)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityForumPic> getCommunityForumPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(社区论坛的帖子图片)信息
	 * @param id
	 * @return
	 */
	public CommunityForumPic getCommunityForumPicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(社区论坛的帖子图片)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityForumPicCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(社区论坛的帖子图片)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityForumPicCountDim(Map<String,Object> paramMap);
	/**
	 * 往(社区论坛的帖子图片)新增一条记录
	 * @param communityForumPic
	 * @return
	 */
	public int insertCommunityForumPic(CommunityForumPic communityForumPic);
	/**
	 * 批量新增(社区论坛的帖子图片)
	 * @param communityForumPicList
	 * @return
	 */
	public int insertCommunityForumPicBatch(List<CommunityForumPic> communityForumPicList);
	/**
	 * 更新(社区论坛的帖子图片)信息
	 * @param communityForumPic
	 * @return
	 */
	public int updateCommunityForumPic(CommunityForumPic communityForumPic);
	/**
	 * 批量更新(社区论坛的帖子图片)信息
	 * @param communityForumPicList
	 * @return
	 */
	public int updateCommunityForumPicBatch(List<CommunityForumPic> communityForumPicList);
	/**
	 * 根据序列号删除(社区论坛的帖子图片)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunityForumPicLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(社区论坛的帖子图片)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunityForumPicLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(社区论坛的帖子图片)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunityForumPic(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(社区论坛的帖子图片)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunityForumPicBatch(List<java.math.BigInteger> idList);
	
}
