package com.cnfantasia.server.domainbase.revenueApply.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;

/**
 * 描述:(提款申请记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRevenueApplyBaseService {
	
	/**
	 * 根据条件查询(提款申请记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RevenueApply> getRevenueApplyByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(提款申请记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RevenueApply> getRevenueApplyByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(提款申请记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RevenueApply> getRevenueApplyByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(提款申请记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RevenueApply> getRevenueApplyByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(提款申请记录)信息
	 * @param id
	 * @return
	 */
	public RevenueApply getRevenueApplyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(提款申请记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRevenueApplyCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(提款申请记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRevenueApplyCountDim(Map<String,Object> paramMap);
	/**
	 * 往(提款申请记录)新增一条记录
	 * @param revenueApply
	 * @return
	 */
	public int insertRevenueApply(RevenueApply revenueApply);
	/**
	 * 批量新增(提款申请记录)
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
	 * 根据序列号批量删除(提款申请记录)信息_逻辑删除
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
