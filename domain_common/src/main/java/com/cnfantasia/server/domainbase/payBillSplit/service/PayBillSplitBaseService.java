package com.cnfantasia.server.domainbase.payBillSplit.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.payBillSplit.dao.IPayBillSplitBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payBillSplit.entity.PayBillSplit;

/**
 * 描述:(费用账单拆分表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PayBillSplitBaseService implements IPayBillSplitBaseService{
	
	private IPayBillSplitBaseDao payBillSplitBaseDao;
	public void setPayBillSplitBaseDao(IPayBillSplitBaseDao payBillSplitBaseDao) {
		this.payBillSplitBaseDao = payBillSplitBaseDao;
	}
	/**
	 * 根据条件查询(费用账单拆分表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayBillSplit> getPayBillSplitByCondition(Map<String,Object> paramMap){
		return payBillSplitBaseDao.selectPayBillSplitByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(费用账单拆分表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayBillSplit> getPayBillSplitByConditionDim(Map<String,Object> paramMap){
		return payBillSplitBaseDao.selectPayBillSplitByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(费用账单拆分表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayBillSplit> getPayBillSplitByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return payBillSplitBaseDao.selectPayBillSplitByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(费用账单拆分表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayBillSplit> getPayBillSplitByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return payBillSplitBaseDao.selectPayBillSplitByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(费用账单拆分表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayBillSplit getPayBillSplitBySeqId(java.math.BigInteger id){
		return payBillSplitBaseDao.selectPayBillSplitBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(费用账单拆分表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayBillSplitCount(Map<String,Object> paramMap){
		return payBillSplitBaseDao.selectPayBillSplitCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(费用账单拆分表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayBillSplitCountDim(Map<String,Object> paramMap){
		return payBillSplitBaseDao.selectPayBillSplitCount(paramMap,true);
	}
	/**
	 * 往(费用账单拆分表)新增一条记录
	 * @param payBillSplit
	 * @return
	 */
	@Override
	public int insertPayBillSplit(PayBillSplit payBillSplit){
		return payBillSplitBaseDao.insertPayBillSplit(payBillSplit);
	}
	/**
	 * 批量新增(费用账单拆分表)
	 * @param payBillSplitList
	 * @return
	 */
	@Override
	public int insertPayBillSplitBatch(List<PayBillSplit> payBillSplitList){
		return payBillSplitBaseDao.insertPayBillSplitBatch(payBillSplitList);
	}
	/**
	 * 更新(费用账单拆分表)信息
	 * @param payBillSplit
	 * @return
	 */
	@Override
	public int updatePayBillSplit(PayBillSplit payBillSplit){
		return payBillSplitBaseDao.updatePayBillSplit(payBillSplit);
	}
	/**
	 * 批量更新(费用账单拆分表)信息
	 * @param payBillSplitList
	 * @return
	 */
	@Override
	public int updatePayBillSplitBatch(List<PayBillSplit> payBillSplitList){
		return payBillSplitBaseDao.updatePayBillSplitBatch(payBillSplitList);
	}
	/**
	 * 根据序列号删除(费用账单拆分表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayBillSplitLogic(java.math.BigInteger id){
		return payBillSplitBaseDao.deletePayBillSplitLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(费用账单拆分表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayBillSplitLogicBatch(List<java.math.BigInteger> idList){
		return payBillSplitBaseDao.deletePayBillSplitLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(费用账单拆分表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayBillSplit(java.math.BigInteger id){
//		return payBillSplitBaseDao.deletePayBillSplit(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(费用账单拆分表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayBillSplitBatch(List<java.math.BigInteger> idList){
//		return payBillSplitBaseDao.deletePayBillSplitBatch(idList);
//	}
	
}
