package com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.entity.RevenueSignalAmountEbuy;

/**
 * 描述:(楼下店收益明细补充表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRevenueSignalAmountEbuyBaseService {
	
	/**
	 * 根据条件查询(楼下店收益明细补充表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RevenueSignalAmountEbuy> getRevenueSignalAmountEbuyByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(楼下店收益明细补充表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RevenueSignalAmountEbuy> getRevenueSignalAmountEbuyByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(楼下店收益明细补充表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RevenueSignalAmountEbuy> getRevenueSignalAmountEbuyByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(楼下店收益明细补充表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RevenueSignalAmountEbuy> getRevenueSignalAmountEbuyByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(楼下店收益明细补充表)信息
	 * @param id
	 * @return
	 */
	public RevenueSignalAmountEbuy getRevenueSignalAmountEbuyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(楼下店收益明细补充表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRevenueSignalAmountEbuyCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(楼下店收益明细补充表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRevenueSignalAmountEbuyCountDim(Map<String,Object> paramMap);
	/**
	 * 往(楼下店收益明细补充表)新增一条记录
	 * @param revenueSignalAmountEbuy
	 * @return
	 */
	public int insertRevenueSignalAmountEbuy(RevenueSignalAmountEbuy revenueSignalAmountEbuy);
	/**
	 * 批量新增(楼下店收益明细补充表)
	 * @param revenueSignalAmountEbuyList
	 * @return
	 */
	public int insertRevenueSignalAmountEbuyBatch(List<RevenueSignalAmountEbuy> revenueSignalAmountEbuyList);
	/**
	 * 更新(楼下店收益明细补充表)信息
	 * @param revenueSignalAmountEbuy
	 * @return
	 */
	public int updateRevenueSignalAmountEbuy(RevenueSignalAmountEbuy revenueSignalAmountEbuy);
	/**
	 * 批量更新(楼下店收益明细补充表)信息
	 * @param revenueSignalAmountEbuyList
	 * @return
	 */
	public int updateRevenueSignalAmountEbuyBatch(List<RevenueSignalAmountEbuy> revenueSignalAmountEbuyList);
	/**
	 * 根据序列号删除(楼下店收益明细补充表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRevenueSignalAmountEbuyLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(楼下店收益明细补充表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRevenueSignalAmountEbuyLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(楼下店收益明细补充表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRevenueSignalAmountEbuy(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(楼下店收益明细补充表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRevenueSignalAmountEbuyBatch(List<java.math.BigInteger> idList);
	
}
