package com.cnfantasia.server.domainbase.commFeedbackLabel.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commFeedbackLabel.entity.CommFeedbackLabel;

/**
 * 描述:(意见反馈的标签表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommFeedbackLabelBaseService {
	
	/**
	 * 根据条件查询(意见反馈的标签表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommFeedbackLabel> getCommFeedbackLabelByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(意见反馈的标签表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommFeedbackLabel> getCommFeedbackLabelByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(意见反馈的标签表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommFeedbackLabel> getCommFeedbackLabelByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(意见反馈的标签表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommFeedbackLabel> getCommFeedbackLabelByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(意见反馈的标签表)信息
	 * @param id
	 * @return
	 */
	public CommFeedbackLabel getCommFeedbackLabelBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(意见反馈的标签表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommFeedbackLabelCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(意见反馈的标签表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommFeedbackLabelCountDim(Map<String,Object> paramMap);
	/**
	 * 往(意见反馈的标签表)新增一条记录
	 * @param commFeedbackLabel
	 * @return
	 */
	public int insertCommFeedbackLabel(CommFeedbackLabel commFeedbackLabel);
	/**
	 * 批量新增(意见反馈的标签表)
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
	 * 根据序列号批量删除(意见反馈的标签表)信息_逻辑删除
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
