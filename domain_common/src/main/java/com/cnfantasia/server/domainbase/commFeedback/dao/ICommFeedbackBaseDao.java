package com.cnfantasia.server.domainbase.commFeedback.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commFeedback.entity.CommFeedback;

/**
 * 描述:(意见反馈表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommFeedbackBaseDao {
	/**
	 * 根据条件查询(意见反馈表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommFeedback> selectCommFeedbackByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(意见反馈表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommFeedback> selectCommFeedbackByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(意见反馈表)信息
	 * @param id
	 * @return
	 */
	public CommFeedback selectCommFeedbackBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(意见反馈表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommFeedbackCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(意见反馈表)新增一条记录
	 * @param commFeedback
	 * @return
	 */
	public int insertCommFeedback(CommFeedback commFeedback);
	
	/**
	 * 批量新增(意见反馈表)信息
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
	 * 根据Id 批量删除(意见反馈表)信息_逻辑删除
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
