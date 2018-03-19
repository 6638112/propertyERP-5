package com.cnfantasia.server.domainbase.revenueApply.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;

/**
 * 描述:(提款申请记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRevenueApplyBaseDao {
	/**
	 * 根据条件查询(提款申请记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RevenueApply> selectRevenueApplyByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(提款申请记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RevenueApply> selectRevenueApplyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(提款申请记录)信息
	 * @param id
	 * @return
	 */
	public RevenueApply selectRevenueApplyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(提款申请记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectRevenueApplyCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(提款申请记录)新增一条记录
	 * @param revenueApply
	 * @return
	 */
	public int insertRevenueApply(RevenueApply revenueApply);
	
	/**
	 * 批量新增(提款申请记录)信息
	 * @param revenueApplyList
	 * @return
	 */
	public int insertRevenueApplyBatch(List<RevenueApply> revenueApplyList);
	
	/**
	 * 更新(提款申请记录)信息
	 * @param revenueApply
	 * @return
	 */
	public int updateRevenueApply(RevenueApply revenueApply);
	
	/**
	 * 批量更新(提款申请记录)信息
	 * @param revenueApplyList
	 * @return
	 */
	public int updateRevenueApplyBatch(List<RevenueApply> revenueApplyList);
	
	/**
	 * 根据序列号删除(提款申请记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRevenueApplyLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(提款申请记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRevenueApplyLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(提款申请记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRevenueApply(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(提款申请记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRevenueApplyBatch(List<java.math.BigInteger> idList);
	
	
}
