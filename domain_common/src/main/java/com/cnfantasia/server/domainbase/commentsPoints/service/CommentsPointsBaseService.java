package com.cnfantasia.server.domainbase.commentsPoints.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commentsPoints.dao.ICommentsPointsBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commentsPoints.entity.CommentsPoints;

/**
 * 描述:(评论的评分项) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommentsPointsBaseService implements ICommentsPointsBaseService{
	
	private ICommentsPointsBaseDao commentsPointsBaseDao;
	public void setCommentsPointsBaseDao(ICommentsPointsBaseDao commentsPointsBaseDao) {
		this.commentsPointsBaseDao = commentsPointsBaseDao;
	}
	/**
	 * 根据条件查询(评论的评分项)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommentsPoints> getCommentsPointsByCondition(Map<String,Object> paramMap){
		return commentsPointsBaseDao.selectCommentsPointsByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(评论的评分项)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommentsPoints> getCommentsPointsByConditionDim(Map<String,Object> paramMap){
		return commentsPointsBaseDao.selectCommentsPointsByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(评论的评分项)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommentsPoints> getCommentsPointsByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commentsPointsBaseDao.selectCommentsPointsByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(评论的评分项)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommentsPoints> getCommentsPointsByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commentsPointsBaseDao.selectCommentsPointsByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(评论的评分项)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommentsPoints getCommentsPointsBySeqId(java.math.BigInteger id){
		return commentsPointsBaseDao.selectCommentsPointsBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(评论的评分项)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommentsPointsCount(Map<String,Object> paramMap){
		return commentsPointsBaseDao.selectCommentsPointsCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(评论的评分项)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommentsPointsCountDim(Map<String,Object> paramMap){
		return commentsPointsBaseDao.selectCommentsPointsCount(paramMap,true);
	}
	/**
	 * 往(评论的评分项)新增一条记录
	 * @param commentsPoints
	 * @return
	 */
	@Override
	public int insertCommentsPoints(CommentsPoints commentsPoints){
		return commentsPointsBaseDao.insertCommentsPoints(commentsPoints);
	}
	/**
	 * 批量新增(评论的评分项)
	 * @param commentsPointsList
	 * @return
	 */
	@Override
	public int insertCommentsPointsBatch(List<CommentsPoints> commentsPointsList){
		return commentsPointsBaseDao.insertCommentsPointsBatch(commentsPointsList);
	}
	/**
	 * 更新(评论的评分项)信息
	 * @param commentsPoints
	 * @return
	 */
	@Override
	public int updateCommentsPoints(CommentsPoints commentsPoints){
		return commentsPointsBaseDao.updateCommentsPoints(commentsPoints);
	}
	/**
	 * 批量更新(评论的评分项)信息
	 * @param commentsPointsList
	 * @return
	 */
	@Override
	public int updateCommentsPointsBatch(List<CommentsPoints> commentsPointsList){
		return commentsPointsBaseDao.updateCommentsPointsBatch(commentsPointsList);
	}
	/**
	 * 根据序列号删除(评论的评分项)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommentsPointsLogic(java.math.BigInteger id){
		return commentsPointsBaseDao.deleteCommentsPointsLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(评论的评分项)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommentsPointsLogicBatch(List<java.math.BigInteger> idList){
		return commentsPointsBaseDao.deleteCommentsPointsLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(评论的评分项)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsPoints(java.math.BigInteger id){
//		return commentsPointsBaseDao.deleteCommentsPoints(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(评论的评分项)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsPointsBatch(List<java.math.BigInteger> idList){
//		return commentsPointsBaseDao.deleteCommentsPointsBatch(idList);
//	}
	
}
