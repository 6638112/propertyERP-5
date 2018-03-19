package com.cnfantasia.server.domainbase.refundRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.refundRecord.dao.IRefundRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.refundRecord.entity.RefundRecord;

/**
 * 描述:(退款记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RefundRecordBaseService implements IRefundRecordBaseService{
	
	private IRefundRecordBaseDao refundRecordBaseDao;
	public void setRefundRecordBaseDao(IRefundRecordBaseDao refundRecordBaseDao) {
		this.refundRecordBaseDao = refundRecordBaseDao;
	}
	/**
	 * 根据条件查询(退款记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RefundRecord> getRefundRecordByCondition(Map<String,Object> paramMap){
		return refundRecordBaseDao.selectRefundRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(退款记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RefundRecord> getRefundRecordByConditionDim(Map<String,Object> paramMap){
		return refundRecordBaseDao.selectRefundRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(退款记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RefundRecord> getRefundRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return refundRecordBaseDao.selectRefundRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(退款记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RefundRecord> getRefundRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return refundRecordBaseDao.selectRefundRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(退款记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public RefundRecord getRefundRecordBySeqId(java.math.BigInteger id){
		return refundRecordBaseDao.selectRefundRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(退款记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRefundRecordCount(Map<String,Object> paramMap){
		return refundRecordBaseDao.selectRefundRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(退款记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRefundRecordCountDim(Map<String,Object> paramMap){
		return refundRecordBaseDao.selectRefundRecordCount(paramMap,true);
	}
	/**
	 * 往(退款记录)新增一条记录
	 * @param refundRecord
	 * @return
	 */
	@Override
	public int insertRefundRecord(RefundRecord refundRecord){
		return refundRecordBaseDao.insertRefundRecord(refundRecord);
	}
	/**
	 * 批量新增(退款记录)
	 * @param refundRecordList
	 * @return
	 */
	@Override
	public int insertRefundRecordBatch(List<RefundRecord> refundRecordList){
		return refundRecordBaseDao.insertRefundRecordBatch(refundRecordList);
	}
	/**
	 * 更新(退款记录)信息
	 * @param refundRecord
	 * @return
	 */
	@Override
	public int updateRefundRecord(RefundRecord refundRecord){
		return refundRecordBaseDao.updateRefundRecord(refundRecord);
	}
	/**
	 * 批量更新(退款记录)信息
	 * @param refundRecordList
	 * @return
	 */
	@Override
	public int updateRefundRecordBatch(List<RefundRecord> refundRecordList){
		return refundRecordBaseDao.updateRefundRecordBatch(refundRecordList);
	}
	/**
	 * 根据序列号删除(退款记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRefundRecordLogic(java.math.BigInteger id){
		return refundRecordBaseDao.deleteRefundRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(退款记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRefundRecordLogicBatch(List<java.math.BigInteger> idList){
		return refundRecordBaseDao.deleteRefundRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(退款记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRefundRecord(java.math.BigInteger id){
//		return refundRecordBaseDao.deleteRefundRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(退款记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRefundRecordBatch(List<java.math.BigInteger> idList){
//		return refundRecordBaseDao.deleteRefundRecordBatch(idList);
//	}
	
}
