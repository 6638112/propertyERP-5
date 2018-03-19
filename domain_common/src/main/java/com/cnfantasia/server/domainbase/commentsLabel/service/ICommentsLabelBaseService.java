package com.cnfantasia.server.domainbase.commentsLabel.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commentsLabel.entity.CommentsLabel;

/**
 * 描述:(评论标签) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommentsLabelBaseService {
	
	/**
	 * 根据条件查询(评论标签)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommentsLabel> getCommentsLabelByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(评论标签)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommentsLabel> getCommentsLabelByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(评论标签)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommentsLabel> getCommentsLabelByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(评论标签)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommentsLabel> getCommentsLabelByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(评论标签)信息
	 * @param id
	 * @return
	 */
	public CommentsLabel getCommentsLabelBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(评论标签)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommentsLabelCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(评论标签)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommentsLabelCountDim(Map<String,Object> paramMap);
	/**
	 * 往(评论标签)新增一条记录
	 * @param commentsLabel
	 * @return
	 */
	public int insertCommentsLabel(CommentsLabel commentsLabel);
	/**
	 * 批量新增(评论标签)
	 * @param commentsLabelList
	 * @return
	 */
	public int insertCommentsLabelBatch(List<CommentsLabel> commentsLabelList);
	/**
	 * 更新(评论标签)信息
	 * @param commentsLabel
	 * @return
	 */
	public int updateCommentsLabel(CommentsLabel commentsLabel);
	/**
	 * 批量更新(评论标签)信息
	 * @param commentsLabelList
	 * @return
	 */
	public int updateCommentsLabelBatch(List<CommentsLabel> commentsLabelList);
	/**
	 * 根据序列号删除(评论标签)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommentsLabelLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(评论标签)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommentsLabelLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(评论标签)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommentsLabel(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(评论标签)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommentsLabelBatch(List<java.math.BigInteger> idList);
	
}
