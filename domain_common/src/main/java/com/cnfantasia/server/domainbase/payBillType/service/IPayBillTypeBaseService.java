package com.cnfantasia.server.domainbase.payBillType.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;

/**
 * 描述:(账单类型基础信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayBillTypeBaseService {
	
	/**
	 * 根据条件查询(账单类型基础信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayBillType> getPayBillTypeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(账单类型基础信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayBillType> getPayBillTypeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(账单类型基础信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayBillType> getPayBillTypeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(账单类型基础信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayBillType> getPayBillTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(账单类型基础信息)信息
	 * @param id
	 * @return
	 */
	public PayBillType getPayBillTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(账单类型基础信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPayBillTypeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(账单类型基础信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPayBillTypeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(账单类型基础信息)新增一条记录
	 * @param payBillType
	 * @return
	 */
	public int insertPayBillType(PayBillType payBillType);
	/**
	 * 批量新增(账单类型基础信息)
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
	 * 根据序列号批量删除(账单类型基础信息)信息_逻辑删除
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
