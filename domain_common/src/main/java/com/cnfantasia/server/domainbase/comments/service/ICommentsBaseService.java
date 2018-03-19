package com.cnfantasia.server.domainbase.comments.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.comments.entity.Comments;

/**
 * 描述:(评论) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommentsBaseService {
	
	/**
	 * 根据条件查询(评论)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<Comments> getCommentsByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(评论)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<Comments> getCommentsByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(评论)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Comments> getCommentsByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(评论)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Comments> getCommentsByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(评论)信息
	 * @param id
	 * @return
	 */
	public Comments getCommentsBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(评论)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommentsCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(评论)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommentsCountDim(Map<String,Object> paramMap);
	/**
	 * 往(评论)新增一条记录
	 * @param comments
	 * @return
	 */
	public int insertComments(Comments comments);
	/**
	 * 批量新增(评论)
	 * @param commentsList
	 * @return
	 */
	public int insertCommentsBatch(List<Comments> commentsList);
	/**
	 * 更新(评论)信息
	 * @param comments
	 * @return
	 */
	public int updateComments(Comments comments);
	/**
	 * 批量更新(评论)信息
	 * @param commentsList
	 * @return
	 */
	public int updateCommentsBatch(List<Comments> commentsList);
	/**
	 * 根据序列号删除(评论)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommentsLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(评论)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommentsLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(评论)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteComments(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(评论)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommentsBatch(List<java.math.BigInteger> idList);
	
}
