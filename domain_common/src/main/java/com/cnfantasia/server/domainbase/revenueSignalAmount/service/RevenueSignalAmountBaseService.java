package com.cnfantasia.server.domainbase.revenueSignalAmount.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.revenueSignalAmount.dao.IRevenueSignalAmountBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;

/**
 * 描述:(单个提款项的收益参数信息信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RevenueSignalAmountBaseService implements IRevenueSignalAmountBaseService{
	
	private IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao;
	public void setRevenueSignalAmountBaseDao(IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao) {
		this.revenueSignalAmountBaseDao = revenueSignalAmountBaseDao;
	}
	/**
	 * 根据条件查询(单个提款项的收益参数信息信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RevenueSignalAmount> getRevenueSignalAmountByCondition(Map<String,Object> paramMap){
		return revenueSignalAmountBaseDao.selectRevenueSignalAmountByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(单个提款项的收益参数信息信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RevenueSignalAmount> getRevenueSignalAmountByConditionDim(Map<String,Object> paramMap){
		return revenueSignalAmountBaseDao.selectRevenueSignalAmountByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(单个提款项的收益参数信息信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RevenueSignalAmount> getRevenueSignalAmountByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return revenueSignalAmountBaseDao.selectRevenueSignalAmountByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(单个提款项的收益参数信息信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RevenueSignalAmount> getRevenueSignalAmountByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return revenueSignalAmountBaseDao.selectRevenueSignalAmountByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(单个提款项的收益参数信息信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public RevenueSignalAmount getRevenueSignalAmountBySeqId(java.math.BigInteger id){
		return revenueSignalAmountBaseDao.selectRevenueSignalAmountBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(单个提款项的收益参数信息信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRevenueSignalAmountCount(Map<String,Object> paramMap){
		return revenueSignalAmountBaseDao.selectRevenueSignalAmountCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(单个提款项的收益参数信息信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRevenueSignalAmountCountDim(Map<String,Object> paramMap){
		return revenueSignalAmountBaseDao.selectRevenueSignalAmountCount(paramMap,true);
	}
	/**
	 * 往(单个提款项的收益参数信息信息)新增一条记录
	 * @param revenueSignalAmount
	 * @return
	 */
	@Override
	public int insertRevenueSignalAmount(RevenueSignalAmount revenueSignalAmount){
		return revenueSignalAmountBaseDao.insertRevenueSignalAmount(revenueSignalAmount);
	}
	/**
	 * 批量新增(单个提款项的收益参数信息信息)
	 * @param revenueSignalAmountList
	 * @return
	 */
	@Override
	public int insertRevenueSignalAmountBatch(List<RevenueSignalAmount> revenueSignalAmountList){
		return revenueSignalAmountBaseDao.insertRevenueSignalAmountBatch(revenueSignalAmountList);
	}
	/**
	 * 更新(单个提款项的收益参数信息信息)信息
	 * @param revenueSignalAmount
	 * @return
	 */
	@Override
	public int updateRevenueSignalAmount(RevenueSignalAmount revenueSignalAmount){
		return revenueSignalAmountBaseDao.updateRevenueSignalAmount(revenueSignalAmount);
	}
	/**
	 * 批量更新(单个提款项的收益参数信息信息)信息
	 * @param revenueSignalAmountList
	 * @return
	 */
	@Override
	public int updateRevenueSignalAmountBatch(List<RevenueSignalAmount> revenueSignalAmountList){
		return revenueSignalAmountBaseDao.updateRevenueSignalAmountBatch(revenueSignalAmountList);
	}
	/**
	 * 根据序列号删除(单个提款项的收益参数信息信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRevenueSignalAmountLogic(java.math.BigInteger id){
		return revenueSignalAmountBaseDao.deleteRevenueSignalAmountLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(单个提款项的收益参数信息信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRevenueSignalAmountLogicBatch(List<java.math.BigInteger> idList){
		return revenueSignalAmountBaseDao.deleteRevenueSignalAmountLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(单个提款项的收益参数信息信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueSignalAmount(java.math.BigInteger id){
//		return revenueSignalAmountBaseDao.deleteRevenueSignalAmount(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(单个提款项的收益参数信息信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueSignalAmountBatch(List<java.math.BigInteger> idList){
//		return revenueSignalAmountBaseDao.deleteRevenueSignalAmountBatch(idList);
//	}
	
}
