package com.cnfantasia.server.domainbase.communityForumPic.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityForumPic.entity.CommunityForumPic;

/**
 * 描述:(社区论坛的帖子图片) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityForumPicBaseDao {
	/**
	 * 根据条件查询(社区论坛的帖子图片)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityForumPic> selectCommunityForumPicByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(社区论坛的帖子图片)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityForumPic> selectCommunityForumPicByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(社区论坛的帖子图片)信息
	 * @param id
	 * @return
	 */
	public CommunityForumPic selectCommunityForumPicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(社区论坛的帖子图片)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunityForumPicCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(社区论坛的帖子图片)新增一条记录
	 * @param communityForumPic
	 * @return
	 */
	public int insertCommunityForumPic(CommunityForumPic communityForumPic);
	
	/**
	 * 批量新增(社区论坛的帖子图片)信息
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
	 * 根据Id 批量删除(社区论坛的帖子图片)信息_逻辑删除
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
