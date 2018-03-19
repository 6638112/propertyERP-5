package com.cnfantasia.server.domainbase.payBill.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payBill.entity.PayBill;

/**
 * 描述:(物业账单) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayBillBaseDao {
	/**
	 * 根据条件查询(物业账单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayBill> selectPayBillByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(物业账单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayBill> selectPayBillByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(物业账单)信息
	 * @param id
	 * @return
	 */
	public PayBill selectPayBillBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业账单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPayBillCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(物业账单)新增一条记录
	 * @param payBill
	 * @return
	 */
	public int insertPayBill(PayBill payBill);
	
	/**
	 * 批量新增(物业账单)信息
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
	 * 根据Id 批量删除(物业账单)信息_逻辑删除
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
