package com.cnfantasia.server.domainbase.commentsHasTCommentsPoints.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commentsHasTCommentsPoints.entity.CommentsHasTCommentsPoints;

/**
 * 描述:(评论包含哪些评分值) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommentsHasTCommentsPointsBaseDao {
	/**
	 * 根据条件查询(评论包含哪些评分值)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommentsHasTCommentsPoints> selectCommentsHasTCommentsPointsByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(评论包含哪些评分值)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommentsHasTCommentsPoints> selectCommentsHasTCommentsPointsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(评论包含哪些评分值)信息
	 * @param id
	 * @return
	 */
	public CommentsHasTCommentsPoints selectCommentsHasTCommentsPointsBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(评论包含哪些评分值)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommentsHasTCommentsPointsCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(评论包含哪些评分值)新增一条记录
	 * @param commentsHasTCommentsPoints
	 * @return
	 */
	public int insertCommentsHasTCommentsPoints(CommentsHasTCommentsPoints commentsHasTCommentsPoints);
	
	/**
	 * 批量新增(评论包含哪些评分值)信息
	 * @param commentsHasTCommentsPointsList
	 * @return
	 */
	public int insertCommentsHasTCommentsPointsBatch(List<CommentsHasTCommentsPoints> commentsHasTCommentsPointsList);
	
	/**
	 * 更新(评论包含哪些评分值)信息
	 * @param commentsHasTCommentsPoints
	 * @return
	 */
	public int updateCommentsHasTCommentsPoints(CommentsHasTCommentsPoints commentsHasTCommentsPoints);
	
	/**
	 * 批量更新(评论包含哪些评分值)信息
	 * @param commentsHasTCommentsPointsList
	 * @return
	 */
	public int updateCommentsHasTCommentsPointsBatch(List<CommentsHasTCommentsPoints> commentsHasTCommentsPointsList);
	
	/**
	 * 根据序列号删除(评论包含哪些评分值)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommentsHasTCommentsPointsLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(评论包含哪些评分值)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommentsHasTCommentsPointsLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(评论包含哪些评分值)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommentsHasTCommentsPoints(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(评论包含哪些评分值)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommentsHasTCommentsPointsBatch(List<java.math.BigInteger> idList);
	
	
}
