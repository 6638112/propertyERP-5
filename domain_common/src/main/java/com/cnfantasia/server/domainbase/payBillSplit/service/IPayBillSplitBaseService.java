package com.cnfantasia.server.domainbase.payBillSplit.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.payBillSplit.entity.PayBillSplit;

/**
 * 描述:(费用账单拆分表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayBillSplitBaseService {
	
	/**
	 * 根据条件查询(费用账单拆分表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayBillSplit> getPayBillSplitByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(费用账单拆分表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayBillSplit> getPayBillSplitByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(费用账单拆分表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayBillSplit> getPayBillSplitByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(费用账单拆分表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayBillSplit> getPayBillSplitByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(费用账单拆分表)信息
	 * @param id
	 * @return
	 */
	public PayBillSplit getPayBillSplitBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(费用账单拆分表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPayBillSplitCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(费用账单拆分表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPayBillSplitCountDim(Map<String,Object> paramMap);
	/**
	 * 往(费用账单拆分表)新增一条记录
	 * @param payBillSplit
	 * @return
	 */
	public int insertPayBillSplit(PayBillSplit payBillSplit);
	/**
	 * 批量新增(费用账单拆分表)
	 * @param payBillSplitList
	 * @return
	 */
	public int insertPayBillSplitBatch(List<PayBillSplit> payBillSplitList);
	/**
	 * 更新(费用账单拆分表)信息
	 * @param payBillSplit
	 * @return
	 */
	public int updatePayBillSplit(PayBillSplit payBillSplit);
	/**
	 * 批量更新(费用账单拆分表)信息
	 * @param payBillSplitList
	 * @return
	 */
	public int updatePayBillSplitBatch(List<PayBillSplit> payBillSplitList);
	/**
	 * 根据序列号删除(费用账单拆分表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePayBillSplitLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(费用账单拆分表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePayBillSplitLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(费用账单拆分表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePayBillSplit(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(费用账单拆分表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePayBillSplitBatch(List<java.math.BigInteger> idList);
	
}
