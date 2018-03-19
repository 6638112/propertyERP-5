package com.cnfantasia.server.domainbase.revenueApply.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.revenueApply.dao.IRevenueApplyBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;

/**
 * 描述:(提款申请记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RevenueApplyBaseService implements IRevenueApplyBaseService{
	
	private IRevenueApplyBaseDao revenueApplyBaseDao;
	public void setRevenueApplyBaseDao(IRevenueApplyBaseDao revenueApplyBaseDao) {
		this.revenueApplyBaseDao = revenueApplyBaseDao;
	}
	/**
	 * 根据条件查询(提款申请记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RevenueApply> getRevenueApplyByCondition(Map<String,Object> paramMap){
		return revenueApplyBaseDao.selectRevenueApplyByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(提款申请记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RevenueApply> getRevenueApplyByConditionDim(Map<String,Object> paramMap){
		return revenueApplyBaseDao.selectRevenueApplyByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(提款申请记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RevenueApply> getRevenueApplyByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return revenueApplyBaseDao.selectRevenueApplyByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(提款申请记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RevenueApply> getRevenueApplyByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return revenueApplyBaseDao.selectRevenueApplyByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(提款申请记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public RevenueApply getRevenueApplyBySeqId(java.math.BigInteger id){
		return revenueApplyBaseDao.selectRevenueApplyBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(提款申请记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRevenueApplyCount(Map<String,Object> paramMap){
		return revenueApplyBaseDao.selectRevenueApplyCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(提款申请记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRevenueApplyCountDim(Map<String,Object> paramMap){
		return revenueApplyBaseDao.selectRevenueApplyCount(paramMap,true);
	}
	/**
	 * 往(提款申请记录)新增一条记录
	 * @param revenueApply
	 * @return
	 */
	@Override
	public int insertRevenueApply(RevenueApply revenueApply){
		return revenueApplyBaseDao.insertRevenueApply(revenueApply);
	}
	/**
	 * 批量新增(提款申请记录)
	 * @param revenueApplyList
	 * @return
	 */
	@Override
	public int insertRevenueApplyBatch(List<RevenueApply> revenueApplyList){
		return revenueApplyBaseDao.insertRevenueApplyBatch(revenueApplyList);
	}
	/**
	 * 更新(提款申请记录)信息
	 * @param revenueApply
	 * @return
	 */
	@Override
	public int updateRevenueApply(RevenueApply revenueApply){
		return revenueApplyBaseDao.updateRevenueApply(revenueApply);
	}
	/**
	 * 批量更新(提款申请记录)信息
	 * @param revenueApplyList
	 * @return
	 */
	@Override
	public int updateRevenueApplyBatch(List<RevenueApply> revenueApplyList){
		return revenueApplyBaseDao.updateRevenueApplyBatch(revenueApplyList);
	}
	/**
	 * 根据序列号删除(提款申请记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRevenueApplyLogic(java.math.BigInteger id){
		return revenueApplyBaseDao.deleteRevenueApplyLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(提款申请记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRevenueApplyLogicBatch(List<java.math.BigInteger> idList){
		return revenueApplyBaseDao.deleteRevenueApplyLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(提款申请记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueApply(java.math.BigInteger id){
//		return revenueApplyBaseDao.deleteRevenueApply(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(提款申请记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueApplyBatch(List<java.math.BigInteger> idList){
//		return revenueApplyBaseDao.deleteRevenueApplyBatch(idList);
//	}
	
}
