package com.cnfantasia.server.domainbase.payRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.payRecord.dao.IPayRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payRecord.entity.PayRecord;

/**
 * 描述:(物业缴费记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PayRecordBaseService implements IPayRecordBaseService{
	
	private IPayRecordBaseDao payRecordBaseDao;
	public void setPayRecordBaseDao(IPayRecordBaseDao payRecordBaseDao) {
		this.payRecordBaseDao = payRecordBaseDao;
	}
	/**
	 * 根据条件查询(物业缴费记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayRecord> getPayRecordByCondition(Map<String,Object> paramMap){
		return payRecordBaseDao.selectPayRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业缴费记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayRecord> getPayRecordByConditionDim(Map<String,Object> paramMap){
		return payRecordBaseDao.selectPayRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业缴费记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayRecord> getPayRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return payRecordBaseDao.selectPayRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业缴费记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayRecord> getPayRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return payRecordBaseDao.selectPayRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业缴费记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayRecord getPayRecordBySeqId(java.math.BigInteger id){
		return payRecordBaseDao.selectPayRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业缴费记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayRecordCount(Map<String,Object> paramMap){
		return payRecordBaseDao.selectPayRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业缴费记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayRecordCountDim(Map<String,Object> paramMap){
		return payRecordBaseDao.selectPayRecordCount(paramMap,true);
	}
	/**
	 * 往(物业缴费记录)新增一条记录
	 * @param payRecord
	 * @return
	 */
	@Override
	public int insertPayRecord(PayRecord payRecord){
		return payRecordBaseDao.insertPayRecord(payRecord);
	}
	/**
	 * 批量新增(物业缴费记录)
	 * @param payRecordList
	 * @return
	 */
	@Override
	public int insertPayRecordBatch(List<PayRecord> payRecordList){
		return payRecordBaseDao.insertPayRecordBatch(payRecordList);
	}
	/**
	 * 更新(物业缴费记录)信息
	 * @param payRecord
	 * @return
	 */
	@Override
	public int updatePayRecord(PayRecord payRecord){
		return payRecordBaseDao.updatePayRecord(payRecord);
	}
	/**
	 * 批量更新(物业缴费记录)信息
	 * @param payRecordList
	 * @return
	 */
	@Override
	public int updatePayRecordBatch(List<PayRecord> payRecordList){
		return payRecordBaseDao.updatePayRecordBatch(payRecordList);
	}
	/**
	 * 根据序列号删除(物业缴费记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayRecordLogic(java.math.BigInteger id){
		return payRecordBaseDao.deletePayRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业缴费记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayRecordLogicBatch(List<java.math.BigInteger> idList){
		return payRecordBaseDao.deletePayRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业缴费记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayRecord(java.math.BigInteger id){
//		return payRecordBaseDao.deletePayRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业缴费记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayRecordBatch(List<java.math.BigInteger> idList){
//		return payRecordBaseDao.deletePayRecordBatch(idList);
//	}
	
}
