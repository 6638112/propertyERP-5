package com.cnfantasia.server.domainbase.communityForumType.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityForumType.entity.CommunityForumType;

/**
 * 描述:(社区论坛的帖子类别) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityForumTypeBaseDao {
	/**
	 * 根据条件查询(社区论坛的帖子类别)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityForumType> selectCommunityForumTypeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(社区论坛的帖子类别)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityForumType> selectCommunityForumTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(社区论坛的帖子类别)信息
	 * @param id
	 * @return
	 */
	public CommunityForumType selectCommunityForumTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(社区论坛的帖子类别)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunityForumTypeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(社区论坛的帖子类别)新增一条记录
	 * @param communityForumType
	 * @return
	 */
	public int insertCommunityForumType(CommunityForumType communityForumType);
	
	/**
	 * 批量新增(社区论坛的帖子类别)信息
	 * @param communityForumTypeList
	 * @return
	 */
	public int insertCommunityForumTypeBatch(List<CommunityForumType> communityForumTypeList);
	
	/**
	 * 更新(社区论坛的帖子类别)信息
	 * @param communityForumType
	 * @return
	 */
	public int updateCommunityForumType(CommunityForumType communityForumType);
	
	/**
	 * 批量更新(社区论坛的帖子类别)信息
	 * @param communityForumTypeList
	 * @return
	 */
	public int updateCommunityForumTypeBatch(List<CommunityForumType> communityForumTypeList);
	
	/**
	 * 根据序列号删除(社区论坛的帖子类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunityForumTypeLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(社区论坛的帖子类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunityForumTypeLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(社区论坛的帖子类别)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunityForumType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(社区论坛的帖子类别)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunityForumTypeBatch(List<java.math.BigInteger> idList);
	
	
}
