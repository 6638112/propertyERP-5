package com.cnfantasia.server.domainbase.revenueSignalAmount.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;

/**
 * 描述:(单个提款项的收益参数信息信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRevenueSignalAmountBaseDao {
	/**
	 * 根据条件查询(单个提款项的收益参数信息信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RevenueSignalAmount> selectRevenueSignalAmountByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(单个提款项的收益参数信息信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RevenueSignalAmount> selectRevenueSignalAmountByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(单个提款项的收益参数信息信息)信息
	 * @param id
	 * @return
	 */
	public RevenueSignalAmount selectRevenueSignalAmountBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(单个提款项的收益参数信息信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectRevenueSignalAmountCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(单个提款项的收益参数信息信息)新增一条记录
	 * @param revenueSignalAmount
	 * @return
	 */
	public int insertRevenueSignalAmount(RevenueSignalAmount revenueSignalAmount);
	
	/**
	 * 批量新增(单个提款项的收益参数信息信息)信息
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
	 * 根据Id 批量删除(单个提款项的收益参数信息信息)信息_逻辑删除
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
