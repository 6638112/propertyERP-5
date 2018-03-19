package com.cnfantasia.server.domainbase.payBillTimeCfg.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payBillTimeCfg.entity.PayBillTimeCfg;

/**
 * 描述:(账单类型对应缴费时间配置) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayBillTimeCfgBaseDao {
	/**
	 * 根据条件查询(账单类型对应缴费时间配置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayBillTimeCfg> selectPayBillTimeCfgByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(账单类型对应缴费时间配置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayBillTimeCfg> selectPayBillTimeCfgByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(账单类型对应缴费时间配置)信息
	 * @param id
	 * @return
	 */
	public PayBillTimeCfg selectPayBillTimeCfgBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(账单类型对应缴费时间配置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPayBillTimeCfgCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(账单类型对应缴费时间配置)新增一条记录
	 * @param payBillTimeCfg
	 * @return
	 */
	public int insertPayBillTimeCfg(PayBillTimeCfg payBillTimeCfg);
	
	/**
	 * 批量新增(账单类型对应缴费时间配置)信息
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
	 * 根据Id 批量删除(账单类型对应缴费时间配置)信息_逻辑删除
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
