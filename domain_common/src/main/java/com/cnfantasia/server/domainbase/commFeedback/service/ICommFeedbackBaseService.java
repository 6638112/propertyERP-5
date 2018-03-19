package com.cnfantasia.server.domainbase.commFeedback.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commFeedback.entity.CommFeedback;

/**
 * 描述:(意见反馈表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommFeedbackBaseService {
	
	/**
	 * 根据条件查询(意见反馈表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommFeedback> getCommFeedbackByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(意见反馈表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommFeedback> getCommFeedbackByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(意见反馈表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommFeedback> getCommFeedbackByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(意见反馈表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommFeedback> getCommFeedbackByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(意见反馈表)信息
	 * @param id
	 * @return
	 */
	public CommFeedback getCommFeedbackBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(意见反馈表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommFeedbackCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(意见反馈表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommFeedbackCountDim(Map<String,Object> paramMap);
	/**
	 * 往(意见反馈表)新增一条记录
	 * @param commFeedback
	 * @return
	 */
	public int insertCommFeedback(CommFeedback commFeedback);
	/**
	 * 批量新增(意见反馈表)
	 * @param commFeedbackList
	 * @return
	 */
	public int insertCommFeedbackBatch(List<CommFeedback> commFeedbackList);
	/**
	 * 更新(意见反馈表)信息
	 * @param commFeedback
	 * @return
	 */
	public int updateCommFeedback(CommFeedback commFeedback);
	/**
	 * 批量更新(意见反馈表)信息
	 * @param commFeedbackList
	 * @return
	 */
	public int updateCommFeedbackBatch(List<CommFeedback> commFeedbackList);
	/**
	 * 根据序列号删除(意见反馈表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommFeedbackLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(意见反馈表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommFeedbackLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(意见反馈表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommFeedback(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(意见反馈表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommFeedbackBatch(List<java.math.BigInteger> idList);
	
}
