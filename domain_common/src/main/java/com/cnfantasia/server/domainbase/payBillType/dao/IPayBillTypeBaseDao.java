package com.cnfantasia.server.domainbase.payBillType.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;

/**
 * 描述:(账单类型基础信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayBillTypeBaseDao {
	/**
	 * 根据条件查询(账单类型基础信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayBillType> selectPayBillTypeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(账单类型基础信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayBillType> selectPayBillTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(账单类型基础信息)信息
	 * @param id
	 * @return
	 */
	public PayBillType selectPayBillTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(账单类型基础信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPayBillTypeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(账单类型基础信息)新增一条记录
	 * @param payBillType
	 * @return
	 */
	public int insertPayBillType(PayBillType payBillType);
	
	/**
	 * 批量新增(账单类型基础信息)信息
	 * @param payBillTypeList
	 * @return
	 */
	public int insertPayBillTypeBatch(List<PayBillType> payBillTypeList);
	
	/**
	 * 更新(账单类型基础信息)信息
	 * @param payBillType
	 * @return
	 */
	public int updatePayBillType(PayBillType payBillType);
	
	/**
	 * 批量更新(账单类型基础信息)信息
	 * @param payBillTypeList
	 * @return
	 */
	public int updatePayBillTypeBatch(List<PayBillType> payBillTypeList);
	
	/**
	 * 根据序列号删除(账单类型基础信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePayBillTypeLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(账单类型基础信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePayBillTypeLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(账单类型基础信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePayBillType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(账单类型基础信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePayBillTypeBatch(List<java.math.BigInteger> idList);
	
	
}
