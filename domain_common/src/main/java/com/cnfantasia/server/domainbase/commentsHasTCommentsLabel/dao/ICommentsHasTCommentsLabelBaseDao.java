package com.cnfantasia.server.domainbase.commentsHasTCommentsLabel.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commentsHasTCommentsLabel.entity.CommentsHasTCommentsLabel;

/**
 * 描述:(评论包含的标签信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommentsHasTCommentsLabelBaseDao {
	/**
	 * 根据条件查询(评论包含的标签信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommentsHasTCommentsLabel> selectCommentsHasTCommentsLabelByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(评论包含的标签信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommentsHasTCommentsLabel> selectCommentsHasTCommentsLabelByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(评论包含的标签信息)信息
	 * @param id
	 * @return
	 */
	public CommentsHasTCommentsLabel selectCommentsHasTCommentsLabelBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(评论包含的标签信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommentsHasTCommentsLabelCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(评论包含的标签信息)新增一条记录
	 * @param commentsHasTCommentsLabel
	 * @return
	 */
	public int insertCommentsHasTCommentsLabel(CommentsHasTCommentsLabel commentsHasTCommentsLabel);
	
	/**
	 * 批量新增(评论包含的标签信息)信息
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
	 * 根据Id 批量删除(评论包含的标签信息)信息_逻辑删除
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
