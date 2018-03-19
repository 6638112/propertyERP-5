package com.cnfantasia.server.domainbase.payBill.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;

/**
 * 描述:(物业账单) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayBillBaseService {
	
	/**
	 * 根据条件查询(物业账单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayBill> getPayBillByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业账单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayBill> getPayBillByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业账单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayBill> getPayBillByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业账单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayBill> getPayBillByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业账单)信息
	 * @param id
	 * @return
	 */
	public PayBill getPayBillBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业账单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPayBillCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业账单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPayBillCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业账单)新增一条记录
	 * @param payBill
	 * @return
	 */
	public int insertPayBill(PayBill payBill);
	/**
	 * 批量新增(物业账单)
	 * @param payBillList
	 * @return
	 */
	public int insertPayBillBatch(List<PayBill> payBillList);
	/**
	 * 更新(物业账单)信息
	 * @param payBill
	 * @return
	 */
	public int updatePayBill(PayBill payBill);
	/**
	 * 批量更新(物业账单)信息
	 * @param payBillList
	 * @return
	 */
	public int updatePayBillBatch(List<PayBill> payBillList);
	/**
	 * 根据序列号删除(物业账单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePayBillLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业账单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePayBillLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业账单)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePayBill(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业账单)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePayBillBatch(List<java.math.BigInteger> idList);
	
}
