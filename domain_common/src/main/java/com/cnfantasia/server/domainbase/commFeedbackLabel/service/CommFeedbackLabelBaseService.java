package com.cnfantasia.server.domainbase.commFeedbackLabel.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commFeedbackLabel.dao.ICommFeedbackLabelBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commFeedbackLabel.entity.CommFeedbackLabel;

/**
 * 描述:(意见反馈的标签表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommFeedbackLabelBaseService implements ICommFeedbackLabelBaseService{
	
	private ICommFeedbackLabelBaseDao commFeedbackLabelBaseDao;
	public void setCommFeedbackLabelBaseDao(ICommFeedbackLabelBaseDao commFeedbackLabelBaseDao) {
		this.commFeedbackLabelBaseDao = commFeedbackLabelBaseDao;
	}
	/**
	 * 根据条件查询(意见反馈的标签表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommFeedbackLabel> getCommFeedbackLabelByCondition(Map<String,Object> paramMap){
		return commFeedbackLabelBaseDao.selectCommFeedbackLabelByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(意见反馈的标签表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommFeedbackLabel> getCommFeedbackLabelByConditionDim(Map<String,Object> paramMap){
		return commFeedbackLabelBaseDao.selectCommFeedbackLabelByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(意见反馈的标签表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommFeedbackLabel> getCommFeedbackLabelByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commFeedbackLabelBaseDao.selectCommFeedbackLabelByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(意见反馈的标签表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommFeedbackLabel> getCommFeedbackLabelByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commFeedbackLabelBaseDao.selectCommFeedbackLabelByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(意见反馈的标签表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommFeedbackLabel getCommFeedbackLabelBySeqId(java.math.BigInteger id){
		return commFeedbackLabelBaseDao.selectCommFeedbackLabelBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(意见反馈的标签表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommFeedbackLabelCount(Map<String,Object> paramMap){
		return commFeedbackLabelBaseDao.selectCommFeedbackLabelCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(意见反馈的标签表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommFeedbackLabelCountDim(Map<String,Object> paramMap){
		return commFeedbackLabelBaseDao.selectCommFeedbackLabelCount(paramMap,true);
	}
	/**
	 * 往(意见反馈的标签表)新增一条记录
	 * @param commFeedbackLabel
	 * @return
	 */
	@Override
	public int insertCommFeedbackLabel(CommFeedbackLabel commFeedbackLabel){
		return commFeedbackLabelBaseDao.insertCommFeedbackLabel(commFeedbackLabel);
	}
	/**
	 * 批量新增(意见反馈的标签表)
	 * @param commFeedbackLabelList
	 * @return
	 */
	@Override
	public int insertCommFeedbackLabelBatch(List<CommFeedbackLabel> commFeedbackLabelList){
		return commFeedbackLabelBaseDao.insertCommFeedbackLabelBatch(commFeedbackLabelList);
	}
	/**
	 * 更新(意见反馈的标签表)信息
	 * @param commFeedbackLabel
	 * @return
	 */
	@Override
	public int updateCommFeedbackLabel(CommFeedbackLabel commFeedbackLabel){
		return commFeedbackLabelBaseDao.updateCommFeedbackLabel(commFeedbackLabel);
	}
	/**
	 * 批量更新(意见反馈的标签表)信息
	 * @param commFeedbackLabelList
	 * @return
	 */
	@Override
	public int updateCommFeedbackLabelBatch(List<CommFeedbackLabel> commFeedbackLabelList){
		return commFeedbackLabelBaseDao.updateCommFeedbackLabelBatch(commFeedbackLabelList);
	}
	/**
	 * 根据序列号删除(意见反馈的标签表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommFeedbackLabelLogic(java.math.BigInteger id){
		return commFeedbackLabelBaseDao.deleteCommFeedbackLabelLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(意见反馈的标签表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommFeedbackLabelLogicBatch(List<java.math.BigInteger> idList){
		return commFeedbackLabelBaseDao.deleteCommFeedbackLabelLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(意见反馈的标签表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommFeedbackLabel(java.math.BigInteger id){
//		return commFeedbackLabelBaseDao.deleteCommFeedbackLabel(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(意见反馈的标签表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommFeedbackLabelBatch(List<java.math.BigInteger> idList){
//		return commFeedbackLabelBaseDao.deleteCommFeedbackLabelBatch(idList);
//	}
	
}
