package com.cnfantasia.server.domainbase.payRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.payRecord.entity.PayRecord;

/**
 * 描述:(物业缴费记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayRecordBaseService {
	
	/**
	 * 根据条件查询(物业缴费记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayRecord> getPayRecordByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业缴费记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayRecord> getPayRecordByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业缴费记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayRecord> getPayRecordByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业缴费记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayRecord> getPayRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业缴费记录)信息
	 * @param id
	 * @return
	 */
	public PayRecord getPayRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业缴费记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPayRecordCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业缴费记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPayRecordCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业缴费记录)新增一条记录
	 * @param payRecord
	 * @return
	 */
	public int insertPayRecord(PayRecord payRecord);
	/**
	 * 批量新增(物业缴费记录)
	 * @param payRecordList
	 * @return
	 */
	public int insertPayRecordBatch(List<PayRecord> payRecordList);
	/**
	 * 更新(物业缴费记录)信息
	 * @param payRecord
	 * @return
	 */
	public int updatePayRecord(PayRecord payRecord);
	/**
	 * 批量更新(物业缴费记录)信息
	 * @param payRecordList
	 * @return
	 */
	public int updatePayRecordBatch(List<PayRecord> payRecordList);
	/**
	 * 根据序列号删除(物业缴费记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePayRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业缴费记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePayRecordLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业缴费记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePayRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业缴费记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePayRecordBatch(List<java.math.BigInteger> idList);
	
}
