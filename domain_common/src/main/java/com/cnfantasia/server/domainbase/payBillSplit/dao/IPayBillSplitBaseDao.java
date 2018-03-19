package com.cnfantasia.server.domainbase.payBillSplit.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payBillSplit.entity.PayBillSplit;

/**
 * 描述:(费用账单拆分表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayBillSplitBaseDao {
	/**
	 * 根据条件查询(费用账单拆分表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayBillSplit> selectPayBillSplitByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(费用账单拆分表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayBillSplit> selectPayBillSplitByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(费用账单拆分表)信息
	 * @param id
	 * @return
	 */
	public PayBillSplit selectPayBillSplitBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(费用账单拆分表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPayBillSplitCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(费用账单拆分表)新增一条记录
	 * @param payBillSplit
	 * @return
	 */
	public int insertPayBillSplit(PayBillSplit payBillSplit);
	
	/**
	 * 批量新增(费用账单拆分表)信息
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
	 * 根据Id 批量删除(费用账单拆分表)信息_逻辑删除
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
