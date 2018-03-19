package com.cnfantasia.server.domainbase.commentsLabel.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commentsLabel.dao.ICommentsLabelBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commentsLabel.entity.CommentsLabel;

/**
 * 描述:(评论标签) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommentsLabelBaseService implements ICommentsLabelBaseService{
	
	private ICommentsLabelBaseDao commentsLabelBaseDao;
	public void setCommentsLabelBaseDao(ICommentsLabelBaseDao commentsLabelBaseDao) {
		this.commentsLabelBaseDao = commentsLabelBaseDao;
	}
	/**
	 * 根据条件查询(评论标签)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommentsLabel> getCommentsLabelByCondition(Map<String,Object> paramMap){
		return commentsLabelBaseDao.selectCommentsLabelByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(评论标签)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommentsLabel> getCommentsLabelByConditionDim(Map<String,Object> paramMap){
		return commentsLabelBaseDao.selectCommentsLabelByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(评论标签)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommentsLabel> getCommentsLabelByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commentsLabelBaseDao.selectCommentsLabelByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(评论标签)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommentsLabel> getCommentsLabelByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commentsLabelBaseDao.selectCommentsLabelByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(评论标签)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommentsLabel getCommentsLabelBySeqId(java.math.BigInteger id){
		return commentsLabelBaseDao.selectCommentsLabelBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(评论标签)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommentsLabelCount(Map<String,Object> paramMap){
		return commentsLabelBaseDao.selectCommentsLabelCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(评论标签)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommentsLabelCountDim(Map<String,Object> paramMap){
		return commentsLabelBaseDao.selectCommentsLabelCount(paramMap,true);
	}
	/**
	 * 往(评论标签)新增一条记录
	 * @param commentsLabel
	 * @return
	 */
	@Override
	public int insertCommentsLabel(CommentsLabel commentsLabel){
		return commentsLabelBaseDao.insertCommentsLabel(commentsLabel);
	}
	/**
	 * 批量新增(评论标签)
	 * @param commentsLabelList
	 * @return
	 */
	@Override
	public int insertCommentsLabelBatch(List<CommentsLabel> commentsLabelList){
		return commentsLabelBaseDao.insertCommentsLabelBatch(commentsLabelList);
	}
	/**
	 * 更新(评论标签)信息
	 * @param commentsLabel
	 * @return
	 */
	@Override
	public int updateCommentsLabel(CommentsLabel commentsLabel){
		return commentsLabelBaseDao.updateCommentsLabel(commentsLabel);
	}
	/**
	 * 批量更新(评论标签)信息
	 * @param commentsLabelList
	 * @return
	 */
	@Override
	public int updateCommentsLabelBatch(List<CommentsLabel> commentsLabelList){
		return commentsLabelBaseDao.updateCommentsLabelBatch(commentsLabelList);
	}
	/**
	 * 根据序列号删除(评论标签)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommentsLabelLogic(java.math.BigInteger id){
		return commentsLabelBaseDao.deleteCommentsLabelLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(评论标签)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommentsLabelLogicBatch(List<java.math.BigInteger> idList){
		return commentsLabelBaseDao.deleteCommentsLabelLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(评论标签)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsLabel(java.math.BigInteger id){
//		return commentsLabelBaseDao.deleteCommentsLabel(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(评论标签)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsLabelBatch(List<java.math.BigInteger> idList){
//		return commentsLabelBaseDao.deleteCommentsLabelBatch(idList);
//	}
	
}
