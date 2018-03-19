package com.cnfantasia.server.domainbase.revenueSignalAmount.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;

/**
 * 描述:(单个提款项的收益参数信息信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRevenueSignalAmountBaseService {
	
	/**
	 * 根据条件查询(单个提款项的收益参数信息信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RevenueSignalAmount> getRevenueSignalAmountByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(单个提款项的收益参数信息信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RevenueSignalAmount> getRevenueSignalAmountByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(单个提款项的收益参数信息信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RevenueSignalAmount> getRevenueSignalAmountByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(单个提款项的收益参数信息信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RevenueSignalAmount> getRevenueSignalAmountByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(单个提款项的收益参数信息信息)信息
	 * @param id
	 * @return
	 */
	public RevenueSignalAmount getRevenueSignalAmountBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(单个提款项的收益参数信息信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRevenueSignalAmountCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(单个提款项的收益参数信息信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRevenueSignalAmountCountDim(Map<String,Object> paramMap);
	/**
	 * 往(单个提款项的收益参数信息信息)新增一条记录
	 * @param revenueSignalAmount
	 * @return
	 */
	public int insertRevenueSignalAmount(RevenueSignalAmount revenueSignalAmount);
	/**
	 * 批量新增(单个提款项的收益参数信息信息)
	 * @param revenueSignalAmountList
	 * @return
	 */
	public int insertRevenueSignalAmountBatch(List<RevenueSignalAmount> revenueSignalAmountList);
	/**
	 * 更新(单个提款项的收益参数信息信息)信息
	 * @param revenueSignalAmount
	 * @return
	 */
	public int updateRevenueSignalAmount(RevenueSignalAmount revenueSignalAmount);
	/**
	 * 批量更新(单个提款项的收益参数信息信息)信息
	 * @param revenueSignalAmountList
	 * @return
	 */
	public int updateRevenueSignalAmountBatch(List<RevenueSignalAmount> revenueSignalAmountList);
	/**
	 * 根据序列号删除(单个提款项的收益参数信息信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRevenueSignalAmountLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(单个提款项的收益参数信息信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRevenueSignalAmountLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(单个提款项的收益参数信息信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRevenueSignalAmount(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(单个提款项的收益参数信息信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRevenueSignalAmountBatch(List<java.math.BigInteger> idList);
	
}
