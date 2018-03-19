package com.cnfantasia.server.domainbase.payBillMarkLog.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.payBillMarkLog.entity.PayBillMarkLog;

/**
 * 描述:(物业缴费账单标记日志) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayBillMarkLogBaseService {
	
	/**
	 * 根据条件查询(物业缴费账单标记日志)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayBillMarkLog> getPayBillMarkLogByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业缴费账单标记日志)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayBillMarkLog> getPayBillMarkLogByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业缴费账单标记日志)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayBillMarkLog> getPayBillMarkLogByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业缴费账单标记日志)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayBillMarkLog> getPayBillMarkLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业缴费账单标记日志)信息
	 * @param id
	 * @return
	 */
	public PayBillMarkLog getPayBillMarkLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业缴费账单标记日志)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPayBillMarkLogCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业缴费账单标记日志)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPayBillMarkLogCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业缴费账单标记日志)新增一条记录
	 * @param payBillMarkLog
	 * @return
	 */
	public int insertPayBillMarkLog(PayBillMarkLog payBillMarkLog);
	/**
	 * 批量新增(物业缴费账单标记日志)
	 * @param payBillMarkLogList
	 * @return
	 */
	public int insertPayBillMarkLogBatch(List<PayBillMarkLog> payBillMarkLogList);
	/**
	 * 更新(物业缴费账单标记日志)信息
	 * @param payBillMarkLog
	 * @return
	 */
	public int updatePayBillMarkLog(PayBillMarkLog payBillMarkLog);
	/**
	 * 批量更新(物业缴费账单标记日志)信息
	 * @param payBillMarkLogList
	 * @return
	 */
	public int updatePayBillMarkLogBatch(List<PayBillMarkLog> payBillMarkLogList);
	/**
	 * 根据序列号删除(物业缴费账单标记日志)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePayBillMarkLogLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业缴费账单标记日志)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePayBillMarkLogLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业缴费账单标记日志)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePayBillMarkLog(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业缴费账单标记日志)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePayBillMarkLogBatch(List<java.math.BigInteger> idList);
	
}
