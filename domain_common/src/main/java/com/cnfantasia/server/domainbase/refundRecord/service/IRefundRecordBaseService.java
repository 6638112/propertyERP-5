package com.cnfantasia.server.domainbase.refundRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.refundRecord.entity.RefundRecord;

/**
 * 描述:(退款记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRefundRecordBaseService {
	
	/**
	 * 根据条件查询(退款记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RefundRecord> getRefundRecordByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(退款记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RefundRecord> getRefundRecordByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(退款记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RefundRecord> getRefundRecordByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(退款记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RefundRecord> getRefundRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(退款记录)信息
	 * @param id
	 * @return
	 */
	public RefundRecord getRefundRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(退款记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRefundRecordCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(退款记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRefundRecordCountDim(Map<String,Object> paramMap);
	/**
	 * 往(退款记录)新增一条记录
	 * @param refundRecord
	 * @return
	 */
	public int insertRefundRecord(RefundRecord refundRecord);
	/**
	 * 批量新增(退款记录)
	 * @param refundRecordList
	 * @return
	 */
	public int insertRefundRecordBatch(List<RefundRecord> refundRecordList);
	/**
	 * 更新(退款记录)信息
	 * @param refundRecord
	 * @return
	 */
	public int updateRefundRecord(RefundRecord refundRecord);
	/**
	 * 批量更新(退款记录)信息
	 * @param refundRecordList
	 * @return
	 */
	public int updateRefundRecordBatch(List<RefundRecord> refundRecordList);
	/**
	 * 根据序列号删除(退款记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRefundRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(退款记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRefundRecordLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(退款记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRefundRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(退款记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRefundRecordBatch(List<java.math.BigInteger> idList);
	
}
