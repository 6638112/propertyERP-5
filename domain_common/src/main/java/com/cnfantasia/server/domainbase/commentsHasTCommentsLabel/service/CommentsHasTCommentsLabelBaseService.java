package com.cnfantasia.server.domainbase.commentsHasTCommentsLabel.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commentsHasTCommentsLabel.dao.ICommentsHasTCommentsLabelBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commentsHasTCommentsLabel.entity.CommentsHasTCommentsLabel;

/**
 * 描述:(评论包含的标签信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommentsHasTCommentsLabelBaseService implements ICommentsHasTCommentsLabelBaseService{
	
	private ICommentsHasTCommentsLabelBaseDao commentsHasTCommentsLabelBaseDao;
	public void setCommentsHasTCommentsLabelBaseDao(ICommentsHasTCommentsLabelBaseDao commentsHasTCommentsLabelBaseDao) {
		this.commentsHasTCommentsLabelBaseDao = commentsHasTCommentsLabelBaseDao;
	}
	/**
	 * 根据条件查询(评论包含的标签信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommentsHasTCommentsLabel> getCommentsHasTCommentsLabelByCondition(Map<String,Object> paramMap){
		return commentsHasTCommentsLabelBaseDao.selectCommentsHasTCommentsLabelByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(评论包含的标签信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommentsHasTCommentsLabel> getCommentsHasTCommentsLabelByConditionDim(Map<String,Object> paramMap){
		return commentsHasTCommentsLabelBaseDao.selectCommentsHasTCommentsLabelByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(评论包含的标签信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommentsHasTCommentsLabel> getCommentsHasTCommentsLabelByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commentsHasTCommentsLabelBaseDao.selectCommentsHasTCommentsLabelByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(评论包含的标签信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommentsHasTCommentsLabel> getCommentsHasTCommentsLabelByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commentsHasTCommentsLabelBaseDao.selectCommentsHasTCommentsLabelByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(评论包含的标签信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommentsHasTCommentsLabel getCommentsHasTCommentsLabelBySeqId(java.math.BigInteger id){
		return commentsHasTCommentsLabelBaseDao.selectCommentsHasTCommentsLabelBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(评论包含的标签信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommentsHasTCommentsLabelCount(Map<String,Object> paramMap){
		return commentsHasTCommentsLabelBaseDao.selectCommentsHasTCommentsLabelCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(评论包含的标签信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommentsHasTCommentsLabelCountDim(Map<String,Object> paramMap){
		return commentsHasTCommentsLabelBaseDao.selectCommentsHasTCommentsLabelCount(paramMap,true);
	}
	/**
	 * 往(评论包含的标签信息)新增一条记录
	 * @param commentsHasTCommentsLabel
	 * @return
	 */
	@Override
	public int insertCommentsHasTCommentsLabel(CommentsHasTCommentsLabel commentsHasTCommentsLabel){
		return commentsHasTCommentsLabelBaseDao.insertCommentsHasTCommentsLabel(commentsHasTCommentsLabel);
	}
	/**
	 * 批量新增(评论包含的标签信息)
	 * @param commentsHasTCommentsLabelList
	 * @return
	 */
	@Override
	public int insertCommentsHasTCommentsLabelBatch(List<CommentsHasTCommentsLabel> commentsHasTCommentsLabelList){
		return commentsHasTCommentsLabelBaseDao.insertCommentsHasTCommentsLabelBatch(commentsHasTCommentsLabelList);
	}
	/**
	 * 更新(评论包含的标签信息)信息
	 * @param commentsHasTCommentsLabel
	 * @return
	 */
	@Override
	public int updateCommentsHasTCommentsLabel(CommentsHasTCommentsLabel commentsHasTCommentsLabel){
		return commentsHasTCommentsLabelBaseDao.updateCommentsHasTCommentsLabel(commentsHasTCommentsLabel);
	}
	/**
	 * 批量更新(评论包含的标签信息)信息
	 * @param commentsHasTCommentsLabelList
	 * @return
	 */
	@Override
	public int updateCommentsHasTCommentsLabelBatch(List<CommentsHasTCommentsLabel> commentsHasTCommentsLabelList){
		return commentsHasTCommentsLabelBaseDao.updateCommentsHasTCommentsLabelBatch(commentsHasTCommentsLabelList);
	}
	/**
	 * 根据序列号删除(评论包含的标签信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommentsHasTCommentsLabelLogic(java.math.BigInteger id){
		return commentsHasTCommentsLabelBaseDao.deleteCommentsHasTCommentsLabelLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(评论包含的标签信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommentsHasTCommentsLabelLogicBatch(List<java.math.BigInteger> idList){
		return commentsHasTCommentsLabelBaseDao.deleteCommentsHasTCommentsLabelLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(评论包含的标签信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsHasTCommentsLabel(java.math.BigInteger id){
//		return commentsHasTCommentsLabelBaseDao.deleteCommentsHasTCommentsLabel(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(评论包含的标签信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommentsHasTCommentsLabelBatch(List<java.math.BigInteger> idList){
//		return commentsHasTCommentsLabelBaseDao.deleteCommentsHasTCommentsLabelBatch(idList);
//	}
	
}
