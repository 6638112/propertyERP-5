package com.cnfantasia.server.domainbase.comments.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.comments.entity.Comments;

/**
 * 描述:(评论) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommentsBaseDao {
	/**
	 * 根据条件查询(评论)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<Comments> selectCommentsByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(评论)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<Comments> selectCommentsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(评论)信息
	 * @param id
	 * @return
	 */
	public Comments selectCommentsBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(评论)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommentsCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(评论)新增一条记录
	 * @param comments
	 * @return
	 */
	public int insertComments(Comments comments);
	
	/**
	 * 批量新增(评论)信息
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
	 * 根据Id 批量删除(评论)信息_逻辑删除
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
