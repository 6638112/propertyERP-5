package com.cnfantasia.server.domainbase.payBillTimeCfg.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.payBillTimeCfg.entity.PayBillTimeCfg;

/**
 * 描述:(账单类型对应缴费时间配置) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayBillTimeCfgBaseService {
	
	/**
	 * 根据条件查询(账单类型对应缴费时间配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayBillTimeCfg> getPayBillTimeCfgByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(账单类型对应缴费时间配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayBillTimeCfg> getPayBillTimeCfgByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(账单类型对应缴费时间配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayBillTimeCfg> getPayBillTimeCfgByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(账单类型对应缴费时间配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayBillTimeCfg> getPayBillTimeCfgByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(账单类型对应缴费时间配置)信息
	 * @param id
	 * @return
	 */
	public PayBillTimeCfg getPayBillTimeCfgBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(账单类型对应缴费时间配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPayBillTimeCfgCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(账单类型对应缴费时间配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPayBillTimeCfgCountDim(Map<String,Object> paramMap);
	/**
	 * 往(账单类型对应缴费时间配置)新增一条记录
	 * @param payBillTimeCfg
	 * @return
	 */
	public int insertPayBillTimeCfg(PayBillTimeCfg payBillTimeCfg);
	/**
	 * 批量新增(账单类型对应缴费时间配置)
	 * @param payBillTimeCfgList
	 * @return
	 */
	public int insertPayBillTimeCfgBatch(List<PayBillTimeCfg> payBillTimeCfgList);
	/**
	 * 更新(账单类型对应缴费时间配置)信息
	 * @param payBillTimeCfg
	 * @return
	 */
	public int updatePayBillTimeCfg(PayBillTimeCfg payBillTimeCfg);
	/**
	 * 批量更新(账单类型对应缴费时间配置)信息
	 * @param payBillTimeCfgList
	 * @return
	 */
	public int updatePayBillTimeCfgBatch(List<PayBillTimeCfg> payBillTimeCfgList);
	/**
	 * 根据序列号删除(账单类型对应缴费时间配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePayBillTimeCfgLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(账单类型对应缴费时间配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePayBillTimeCfgLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(账单类型对应缴费时间配置)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePayBillTimeCfg(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(账单类型对应缴费时间配置)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePayBillTimeCfgBatch(List<java.math.BigInteger> idList);
	
}
