package com.cnfantasia.server.domainbase.commentsHasTCommentsPoints.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commentsHasTCommentsPoints.entity.CommentsHasTCommentsPoints;

/**
 * 描述:(评论包含哪些评分值) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommentsHasTCommentsPointsBaseService {
	
	/**
	 * 根据条件查询(评论包含哪些评分值)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommentsHasTCommentsPoints> getCommentsHasTCommentsPointsByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(评论包含哪些评分值)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommentsHasTCommentsPoints> getCommentsHasTCommentsPointsByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(评论包含哪些评分值)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommentsHasTCommentsPoints> getCommentsHasTCommentsPointsByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(评论包含哪些评分值)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommentsHasTCommentsPoints> getCommentsHasTCommentsPointsByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(评论包含哪些评分值)信息
	 * @param id
	 * @return
	 */
	public CommentsHasTCommentsPoints getCommentsHasTCommentsPointsBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(评论包含哪些评分值)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommentsHasTCommentsPointsCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(评论包含哪些评分值)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommentsHasTCommentsPointsCountDim(Map<String,Object> paramMap);
	/**
	 * 往(评论包含哪些评分值)新增一条记录
	 * @param commentsHasTCommentsPoints
	 * @return
	 */
	public int insertCommentsHasTCommentsPoints(CommentsHasTCommentsPoints commentsHasTCommentsPoints);
	/**
	 * 批量新增(评论包含哪些评分值)
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
	 * 根据序列号批量删除(评论包含哪些评分值)信息_逻辑删除
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
