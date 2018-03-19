package com.cnfantasia.server.domainbase.comments.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.comments.dao.ICommentsBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.comments.entity.Comments;

/**
 * 描述:(评论) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommentsBaseService implements ICommentsBaseService{
	
	private ICommentsBaseDao commentsBaseDao;
	public void setCommentsBaseDao(ICommentsBaseDao commentsBaseDao) {
		this.commentsBaseDao = commentsBaseDao;
	}
	/**
	 * 根据条件查询(评论)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Comments> getCommentsByCondition(Map<String,Object> paramMap){
		return commentsBaseDao.selectCommentsByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(评论)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Comments> getCommentsByConditionDim(Map<String,Object> paramMap){
		return commentsBaseDao.selectCommentsByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(评论)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Comments> getCommentsByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commentsBaseDao.selectCommentsByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(评论)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Comments> getCommentsByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commentsBaseDao.selectCommentsByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(评论)信息
	 * @param id
	 * @return
	 */
	@Override
	public Comments getCommentsBySeqId(java.math.BigInteger id){
		return commentsBaseDao.selectCommentsBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(评论)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommentsCount(Map<String,Object> paramMap){
		return commentsBaseDao.selectCommentsCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(评论)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommentsCountDim(Map<String,Object> paramMap){
		return commentsBaseDao.selectCommentsCount(paramMap,true);
	}
	/**
	 * 往(评论)新增一条记录
	 * @param comments
	 * @return
	 */
	@Override
	public int insertComments(Comments comments){
		return commentsBaseDao.insertComments(comments);
	}
	/**
	 * 批量新增(评论)
	 * @param commentsList
	 * @return
	 */
	@Override
	public int insertCommentsBatch(List<Comments> commentsList){
		return commentsBaseDao.insertCommentsBatch(commentsList);
	}
	/**
	 * 更新(评论)信息
	 * @param comments
	 * @return
	 */
	@Override
	public int updateComments(Comments comments){
		return commentsBaseDao.updateComments(comments);
	}
	/**
	 * 批量更新(评论)信息
	 * @param commentsList
	 * @return
	 */
	@Override
	public int updateCommentsBatch(List<Comments> commentsList){
		return commentsBaseDao.updateCommentsBatch(commentsList);
	}
	/**
	 * 根据序列号删除(评论)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommentsLogic(java.math.BigInteger id){
		return commentsBaseDao.deleteCommentsLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(评论)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommentsLogicBatch(List<java.math.BigInteger> idList){
		return commentsBaseDao.deleteCommentsLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(评论)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteComments(java.math.BigInteger id){
//		return commentsBaseDao.deleteComments(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(评论)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsBatch(List<java.math.BigInteger> idList){
//		return commentsBaseDao.deleteCommentsBatch(idList);
//	}
	
}
