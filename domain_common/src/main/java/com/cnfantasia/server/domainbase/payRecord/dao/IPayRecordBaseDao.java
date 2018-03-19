package com.cnfantasia.server.domainbase.payRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payRecord.entity.PayRecord;

/**
 * 描述:(物业缴费记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayRecordBaseDao {
	/**
	 * 根据条件查询(物业缴费记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayRecord> selectPayRecordByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(物业缴费记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayRecord> selectPayRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(物业缴费记录)信息
	 * @param id
	 * @return
	 */
	public PayRecord selectPayRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业缴费记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPayRecordCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(物业缴费记录)新增一条记录
	 * @param payRecord
	 * @return
	 */
	public int insertPayRecord(PayRecord payRecord);
	
	/**
	 * 批量新增(物业缴费记录)信息
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
	 * 根据Id 批量删除(物业缴费记录)信息_逻辑删除
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
