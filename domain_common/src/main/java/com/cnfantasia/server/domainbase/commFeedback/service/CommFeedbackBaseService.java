package com.cnfantasia.server.domainbase.commFeedback.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commFeedback.dao.ICommFeedbackBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commFeedback.entity.CommFeedback;

/**
 * 描述:(意见反馈表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommFeedbackBaseService implements ICommFeedbackBaseService{
	
	private ICommFeedbackBaseDao commFeedbackBaseDao;
	public void setCommFeedbackBaseDao(ICommFeedbackBaseDao commFeedbackBaseDao) {
		this.commFeedbackBaseDao = commFeedbackBaseDao;
	}
	/**
	 * 根据条件查询(意见反馈表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommFeedback> getCommFeedbackByCondition(Map<String,Object> paramMap){
		return commFeedbackBaseDao.selectCommFeedbackByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(意见反馈表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommFeedback> getCommFeedbackByConditionDim(Map<String,Object> paramMap){
		return commFeedbackBaseDao.selectCommFeedbackByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(意见反馈表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommFeedback> getCommFeedbackByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commFeedbackBaseDao.selectCommFeedbackByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(意见反馈表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommFeedback> getCommFeedbackByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commFeedbackBaseDao.selectCommFeedbackByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(意见反馈表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommFeedback getCommFeedbackBySeqId(java.math.BigInteger id){
		return commFeedbackBaseDao.selectCommFeedbackBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(意见反馈表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommFeedbackCount(Map<String,Object> paramMap){
		return commFeedbackBaseDao.selectCommFeedbackCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(意见反馈表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommFeedbackCountDim(Map<String,Object> paramMap){
		return commFeedbackBaseDao.selectCommFeedbackCount(paramMap,true);
	}
	/**
	 * 往(意见反馈表)新增一条记录
	 * @param commFeedback
	 * @return
	 */
	@Override
	public int insertCommFeedback(CommFeedback commFeedback){
		return commFeedbackBaseDao.insertCommFeedback(commFeedback);
	}
	/**
	 * 批量新增(意见反馈表)
	 * @param commFeedbackList
	 * @return
	 */
	@Override
	public int insertCommFeedbackBatch(List<CommFeedback> commFeedbackList){
		return commFeedbackBaseDao.insertCommFeedbackBatch(commFeedbackList);
	}
	/**
	 * 更新(意见反馈表)信息
	 * @param commFeedback
	 * @return
	 */
	@Override
	public int updateCommFeedback(CommFeedback commFeedback){
		return commFeedbackBaseDao.updateCommFeedback(commFeedback);
	}
	/**
	 * 批量更新(意见反馈表)信息
	 * @param commFeedbackList
	 * @return
	 */
	@Override
	public int updateCommFeedbackBatch(List<CommFeedback> commFeedbackList){
		return commFeedbackBaseDao.updateCommFeedbackBatch(commFeedbackList);
	}
	/**
	 * 根据序列号删除(意见反馈表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommFeedbackLogic(java.math.BigInteger id){
		return commFeedbackBaseDao.deleteCommFeedbackLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(意见反馈表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommFeedbackLogicBatch(List<java.math.BigInteger> idList){
		return commFeedbackBaseDao.deleteCommFeedbackLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(意见反馈表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommFeedback(java.math.BigInteger id){
//		return commFeedbackBaseDao.deleteCommFeedback(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(意见反馈表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommFeedbackBatch(List<java.math.BigInteger> idList){
//		return commFeedbackBaseDao.deleteCommFeedbackBatch(idList);
//	}
	
}
