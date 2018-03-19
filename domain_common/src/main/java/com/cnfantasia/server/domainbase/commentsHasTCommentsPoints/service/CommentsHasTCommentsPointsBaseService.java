package com.cnfantasia.server.domainbase.commentsHasTCommentsPoints.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commentsHasTCommentsPoints.dao.ICommentsHasTCommentsPointsBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commentsHasTCommentsPoints.entity.CommentsHasTCommentsPoints;

/**
 * 描述:(评论包含哪些评分值) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommentsHasTCommentsPointsBaseService implements ICommentsHasTCommentsPointsBaseService{
	
	private ICommentsHasTCommentsPointsBaseDao commentsHasTCommentsPointsBaseDao;
	public void setCommentsHasTCommentsPointsBaseDao(ICommentsHasTCommentsPointsBaseDao commentsHasTCommentsPointsBaseDao) {
		this.commentsHasTCommentsPointsBaseDao = commentsHasTCommentsPointsBaseDao;
	}
	/**
	 * 根据条件查询(评论包含哪些评分值)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommentsHasTCommentsPoints> getCommentsHasTCommentsPointsByCondition(Map<String,Object> paramMap){
		return commentsHasTCommentsPointsBaseDao.selectCommentsHasTCommentsPointsByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(评论包含哪些评分值)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommentsHasTCommentsPoints> getCommentsHasTCommentsPointsByConditionDim(Map<String,Object> paramMap){
		return commentsHasTCommentsPointsBaseDao.selectCommentsHasTCommentsPointsByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(评论包含哪些评分值)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommentsHasTCommentsPoints> getCommentsHasTCommentsPointsByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commentsHasTCommentsPointsBaseDao.selectCommentsHasTCommentsPointsByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(评论包含哪些评分值)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommentsHasTCommentsPoints> getCommentsHasTCommentsPointsByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commentsHasTCommentsPointsBaseDao.selectCommentsHasTCommentsPointsByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(评论包含哪些评分值)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommentsHasTCommentsPoints getCommentsHasTCommentsPointsBySeqId(java.math.BigInteger id){
		return commentsHasTCommentsPointsBaseDao.selectCommentsHasTCommentsPointsBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(评论包含哪些评分值)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommentsHasTCommentsPointsCount(Map<String,Object> paramMap){
		return commentsHasTCommentsPointsBaseDao.selectCommentsHasTCommentsPointsCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(评论包含哪些评分值)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommentsHasTCommentsPointsCountDim(Map<String,Object> paramMap){
		return commentsHasTCommentsPointsBaseDao.selectCommentsHasTCommentsPointsCount(paramMap,true);
	}
	/**
	 * 往(评论包含哪些评分值)新增一条记录
	 * @param commentsHasTCommentsPoints
	 * @return
	 */
	@Override
	public int insertCommentsHasTCommentsPoints(CommentsHasTCommentsPoints commentsHasTCommentsPoints){
		return commentsHasTCommentsPointsBaseDao.insertCommentsHasTCommentsPoints(commentsHasTCommentsPoints);
	}
	/**
	 * 批量新增(评论包含哪些评分值)
	 * @param commentsHasTCommentsPointsList
	 * @return
	 */
	@Override
	public int insertCommentsHasTCommentsPointsBatch(List<CommentsHasTCommentsPoints> commentsHasTCommentsPointsList){
		return commentsHasTCommentsPointsBaseDao.insertCommentsHasTCommentsPointsBatch(commentsHasTCommentsPointsList);
	}
	/**
	 * 更新(评论包含哪些评分值)信息
	 * @param commentsHasTCommentsPoints
	 * @return
	 */
	@Override
	public int updateCommentsHasTCommentsPoints(CommentsHasTCommentsPoints commentsHasTCommentsPoints){
		return commentsHasTCommentsPointsBaseDao.updateCommentsHasTCommentsPoints(commentsHasTCommentsPoints);
	}
	/**
	 * 批量更新(评论包含哪些评分值)信息
	 * @param commentsHasTCommentsPointsList
	 * @return
	 */
	@Override
	public int updateCommentsHasTCommentsPointsBatch(List<CommentsHasTCommentsPoints> commentsHasTCommentsPointsList){
		return commentsHasTCommentsPointsBaseDao.updateCommentsHasTCommentsPointsBatch(commentsHasTCommentsPointsList);
	}
	/**
	 * 根据序列号删除(评论包含哪些评分值)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommentsHasTCommentsPointsLogic(java.math.BigInteger id){
		return commentsHasTCommentsPointsBaseDao.deleteCommentsHasTCommentsPointsLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(评论包含哪些评分值)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommentsHasTCommentsPointsLogicBatch(List<java.math.BigInteger> idList){
		return commentsHasTCommentsPointsBaseDao.deleteCommentsHasTCommentsPointsLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(评论包含哪些评分值)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsHasTCommentsPoints(java.math.BigInteger id){
//		return commentsHasTCommentsPointsBaseDao.deleteCommentsHasTCommentsPoints(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(评论包含哪些评分值)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsHasTCommentsPointsBatch(List<java.math.BigInteger> idList){
//		return commentsHasTCommentsPointsBaseDao.deleteCommentsHasTCommentsPointsBatch(idList);
//	}
	
}
