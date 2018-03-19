package com.cnfantasia.server.domainbase.payBillMarkLog.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.payBillMarkLog.dao.IPayBillMarkLogBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payBillMarkLog.entity.PayBillMarkLog;

/**
 * 描述:(物业缴费账单标记日志) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PayBillMarkLogBaseService implements IPayBillMarkLogBaseService{
	
	private IPayBillMarkLogBaseDao payBillMarkLogBaseDao;
	public void setPayBillMarkLogBaseDao(IPayBillMarkLogBaseDao payBillMarkLogBaseDao) {
		this.payBillMarkLogBaseDao = payBillMarkLogBaseDao;
	}
	/**
	 * 根据条件查询(物业缴费账单标记日志)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayBillMarkLog> getPayBillMarkLogByCondition(Map<String,Object> paramMap){
		return payBillMarkLogBaseDao.selectPayBillMarkLogByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业缴费账单标记日志)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayBillMarkLog> getPayBillMarkLogByConditionDim(Map<String,Object> paramMap){
		return payBillMarkLogBaseDao.selectPayBillMarkLogByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业缴费账单标记日志)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayBillMarkLog> getPayBillMarkLogByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return payBillMarkLogBaseDao.selectPayBillMarkLogByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业缴费账单标记日志)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayBillMarkLog> getPayBillMarkLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return payBillMarkLogBaseDao.selectPayBillMarkLogByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业缴费账单标记日志)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayBillMarkLog getPayBillMarkLogBySeqId(java.math.BigInteger id){
		return payBillMarkLogBaseDao.selectPayBillMarkLogBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业缴费账单标记日志)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayBillMarkLogCount(Map<String,Object> paramMap){
		return payBillMarkLogBaseDao.selectPayBillMarkLogCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业缴费账单标记日志)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayBillMarkLogCountDim(Map<String,Object> paramMap){
		return payBillMarkLogBaseDao.selectPayBillMarkLogCount(paramMap,true);
	}
	/**
	 * 往(物业缴费账单标记日志)新增一条记录
	 * @param payBillMarkLog
	 * @return
	 */
	@Override
	public int insertPayBillMarkLog(PayBillMarkLog payBillMarkLog){
		return payBillMarkLogBaseDao.insertPayBillMarkLog(payBillMarkLog);
	}
	/**
	 * 批量新增(物业缴费账单标记日志)
	 * @param payBillMarkLogList
	 * @return
	 */
	@Override
	public int insertPayBillMarkLogBatch(List<PayBillMarkLog> payBillMarkLogList){
		return payBillMarkLogBaseDao.insertPayBillMarkLogBatch(payBillMarkLogList);
	}
	/**
	 * 更新(物业缴费账单标记日志)信息
	 * @param payBillMarkLog
	 * @return
	 */
	@Override
	public int updatePayBillMarkLog(PayBillMarkLog payBillMarkLog){
		return payBillMarkLogBaseDao.updatePayBillMarkLog(payBillMarkLog);
	}
	/**
	 * 批量更新(物业缴费账单标记日志)信息
	 * @param payBillMarkLogList
	 * @return
	 */
	@Override
	public int updatePayBillMarkLogBatch(List<PayBillMarkLog> payBillMarkLogList){
		return payBillMarkLogBaseDao.updatePayBillMarkLogBatch(payBillMarkLogList);
	}
	/**
	 * 根据序列号删除(物业缴费账单标记日志)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayBillMarkLogLogic(java.math.BigInteger id){
		return payBillMarkLogBaseDao.deletePayBillMarkLogLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业缴费账单标记日志)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayBillMarkLogLogicBatch(List<java.math.BigInteger> idList){
		return payBillMarkLogBaseDao.deletePayBillMarkLogLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业缴费账单标记日志)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayBillMarkLog(java.math.BigInteger id){
//		return payBillMarkLogBaseDao.deletePayBillMarkLog(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业缴费账单标记日志)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayBillMarkLogBatch(List<java.math.BigInteger> idList){
//		return payBillMarkLogBaseDao.deletePayBillMarkLogBatch(idList);
//	}
	
}
