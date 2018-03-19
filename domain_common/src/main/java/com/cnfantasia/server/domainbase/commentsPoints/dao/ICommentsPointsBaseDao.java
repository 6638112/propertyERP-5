package com.cnfantasia.server.domainbase.commentsPoints.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commentsPoints.entity.CommentsPoints;

/**
 * 描述:(评论的评分项) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommentsPointsBaseDao {
	/**
	 * 根据条件查询(评论的评分项)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommentsPoints> selectCommentsPointsByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(评论的评分项)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommentsPoints> selectCommentsPointsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(评论的评分项)信息
	 * @param id
	 * @return
	 */
	public CommentsPoints selectCommentsPointsBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(评论的评分项)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommentsPointsCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(评论的评分项)新增一条记录
	 * @param commentsPoints
	 * @return
	 */
	public int insertCommentsPoints(CommentsPoints commentsPoints);
	
	/**
	 * 批量新增(评论的评分项)信息
	 * @param commentsPointsList
	 * @return
	 */
	public int insertCommentsPointsBatch(List<CommentsPoints> commentsPointsList);
	
	/**
	 * 更新(评论的评分项)信息
	 * @param commentsPoints
	 * @return
	 */
	public int updateCommentsPoints(CommentsPoints commentsPoints);
	
	/**
	 * 批量更新(评论的评分项)信息
	 * @param commentsPointsList
	 * @return
	 */
	public int updateCommentsPointsBatch(List<CommentsPoints> commentsPointsList);
	
	/**
	 * 根据序列号删除(评论的评分项)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommentsPointsLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(评论的评分项)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommentsPointsLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(评论的评分项)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommentsPoints(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(评论的评分项)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommentsPointsBatch(List<java.math.BigInteger> idList);
	
	
}
