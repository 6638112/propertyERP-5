package com.cnfantasia.server.domainbase.commFeedbackLabel.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commFeedbackLabel.entity.CommFeedbackLabel;

/**
 * 描述:(意见反馈的标签表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommFeedbackLabelBaseDao {
	/**
	 * 根据条件查询(意见反馈的标签表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommFeedbackLabel> selectCommFeedbackLabelByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(意见反馈的标签表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommFeedbackLabel> selectCommFeedbackLabelByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(意见反馈的标签表)信息
	 * @param id
	 * @return
	 */
	public CommFeedbackLabel selectCommFeedbackLabelBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(意见反馈的标签表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommFeedbackLabelCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(意见反馈的标签表)新增一条记录
	 * @param commFeedbackLabel
	 * @return
	 */
	public int insertCommFeedbackLabel(CommFeedbackLabel commFeedbackLabel);
	
	/**
	 * 批量新增(意见反馈的标签表)信息
	 * @param commFeedbackLabelList
	 * @return
	 */
	public int insertCommFeedbackLabelBatch(List<CommFeedbackLabel> commFeedbackLabelList);
	
	/**
	 * 更新(意见反馈的标签表)信息
	 * @param commFeedbackLabel
	 * @return
	 */
	public int updateCommFeedbackLabel(CommFeedbackLabel commFeedbackLabel);
	
	/**
	 * 批量更新(意见反馈的标签表)信息
	 * @param commFeedbackLabelList
	 * @return
	 */
	public int updateCommFeedbackLabelBatch(List<CommFeedbackLabel> commFeedbackLabelList);
	
	/**
	 * 根据序列号删除(意见反馈的标签表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommFeedbackLabelLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(意见反馈的标签表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommFeedbackLabelLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(意见反馈的标签表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommFeedbackLabel(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(意见反馈的标签表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommFeedbackLabelBatch(List<java.math.BigInteger> idList);
	
	
}
