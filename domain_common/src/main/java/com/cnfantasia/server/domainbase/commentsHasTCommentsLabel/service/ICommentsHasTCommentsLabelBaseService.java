package com.cnfantasia.server.domainbase.commentsHasTCommentsLabel.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commentsHasTCommentsLabel.entity.CommentsHasTCommentsLabel;

/**
 * 描述:(评论包含的标签信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommentsHasTCommentsLabelBaseService {
	
	/**
	 * 根据条件查询(评论包含的标签信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommentsHasTCommentsLabel> getCommentsHasTCommentsLabelByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(评论包含的标签信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommentsHasTCommentsLabel> getCommentsHasTCommentsLabelByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(评论包含的标签信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommentsHasTCommentsLabel> getCommentsHasTCommentsLabelByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(评论包含的标签信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommentsHasTCommentsLabel> getCommentsHasTCommentsLabelByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(评论包含的标签信息)信息
	 * @param id
	 * @return
	 */
	public CommentsHasTCommentsLabel getCommentsHasTCommentsLabelBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(评论包含的标签信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommentsHasTCommentsLabelCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(评论包含的标签信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommentsHasTCommentsLabelCountDim(Map<String,Object> paramMap);
	/**
	 * 往(评论包含的标签信息)新增一条记录
	 * @param commentsHasTCommentsLabel
	 * @return
	 */
	public int insertCommentsHasTCommentsLabel(CommentsHasTCommentsLabel commentsHasTCommentsLabel);
	/**
	 * 批量新增(评论包含的标签信息)
	 * @param commentsHasTCommentsLabelList
	 * @return
	 */
	public int insertCommentsHasTCommentsLabelBatch(List<CommentsHasTCommentsLabel> commentsHasTCommentsLabelList);
	/**
	 * 更新(评论包含的标签信息)信息
	 * @param commentsHasTCommentsLabel
	 * @return
	 */
	public int updateCommentsHasTCommentsLabel(CommentsHasTCommentsLabel commentsHasTCommentsLabel);
	/**
	 * 批量更新(评论包含的标签信息)信息
	 * @param commentsHasTCommentsLabelList
	 * @return
	 */
	public int updateCommentsHasTCommentsLabelBatch(List<CommentsHasTCommentsLabel> commentsHasTCommentsLabelList);
	/**
	 * 根据序列号删除(评论包含的标签信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommentsHasTCommentsLabelLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(评论包含的标签信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommentsHasTCommentsLabelLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(评论包含的标签信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommentsHasTCommentsLabel(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(评论包含的标签信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommentsHasTCommentsLabelBatch(List<java.math.BigInteger> idList);
	
}
