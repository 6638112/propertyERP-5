package com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.entity.RevenueSignalAmountEbuy;

/**
 * 描述:(楼下店收益明细补充表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRevenueSignalAmountEbuyBaseDao {
	/**
	 * 根据条件查询(楼下店收益明细补充表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RevenueSignalAmountEbuy> selectRevenueSignalAmountEbuyByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(楼下店收益明细补充表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RevenueSignalAmountEbuy> selectRevenueSignalAmountEbuyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(楼下店收益明细补充表)信息
	 * @param id
	 * @return
	 */
	public RevenueSignalAmountEbuy selectRevenueSignalAmountEbuyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(楼下店收益明细补充表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectRevenueSignalAmountEbuyCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(楼下店收益明细补充表)新增一条记录
	 * @param revenueSignalAmountEbuy
	 * @return
	 */
	public int insertRevenueSignalAmountEbuy(RevenueSignalAmountEbuy revenueSignalAmountEbuy);
	
	/**
	 * 批量新增(楼下店收益明细补充表)信息
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
	 * 根据Id 批量删除(楼下店收益明细补充表)信息_逻辑删除
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
