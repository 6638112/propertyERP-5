package com.cnfantasia.server.domainbase.communityForumContent.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityForumContent.entity.CommunityForumContent;

/**
 * 描述:(社区论坛的帖子内容) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityForumContentBaseDao {
	/**
	 * 根据条件查询(社区论坛的帖子内容)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityForumContent> selectCommunityForumContentByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(社区论坛的帖子内容)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityForumContent> selectCommunityForumContentByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(社区论坛的帖子内容)信息
	 * @param id
	 * @return
	 */
	public CommunityForumContent selectCommunityForumContentBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(社区论坛的帖子内容)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunityForumContentCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(社区论坛的帖子内容)新增一条记录
	 * @param communityForumContent
	 * @return
	 */
	public int insertCommunityForumContent(CommunityForumContent communityForumContent);
	
	/**
	 * 批量新增(社区论坛的帖子内容)信息
	 * @param communityForumContentList
	 * @return
	 */
	public int insertCommunityForumContentBatch(List<CommunityForumContent> communityForumContentList);
	
	/**
	 * 更新(社区论坛的帖子内容)信息
	 * @param communityForumContent
	 * @return
	 */
	public int updateCommunityForumContent(CommunityForumContent communityForumContent);
	
	/**
	 * 批量更新(社区论坛的帖子内容)信息
	 * @param communityForumContentList
	 * @return
	 */
	public int updateCommunityForumContentBatch(List<CommunityForumContent> communityForumContentList);
	
	/**
	 * 根据序列号删除(社区论坛的帖子内容)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunityForumContentLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(社区论坛的帖子内容)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunityForumContentLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(社区论坛的帖子内容)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunityForumContent(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(社区论坛的帖子内容)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunityForumContentBatch(List<java.math.BigInteger> idList);
	
	
}
