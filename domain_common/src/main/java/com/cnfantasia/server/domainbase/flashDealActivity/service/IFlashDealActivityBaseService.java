package com.cnfantasia.server.domainbase.flashDealActivity.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.flashDealActivity.entity.FlashDealActivity;

/**
 * 描述:(秒杀活动表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IFlashDealActivityBaseService {
	
	/**
	 * 根据条件查询(秒杀活动表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<FlashDealActivity> getFlashDealActivityByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(秒杀活动表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<FlashDealActivity> getFlashDealActivityByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(秒杀活动表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<FlashDealActivity> getFlashDealActivityByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(秒杀活动表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<FlashDealActivity> getFlashDealActivityByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(秒杀活动表)信息
	 * @param id
	 * @return
	 */
	public FlashDealActivity getFlashDealActivityBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(秒杀活动表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getFlashDealActivityCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(秒杀活动表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getFlashDealActivityCountDim(Map<String, Object> paramMap);
	/**
	 * 往(秒杀活动表)新增一条记录
	 * @param flashDealActivity
	 * @return
	 */
	public int insertFlashDealActivity(FlashDealActivity flashDealActivity);
	/**
	 * 批量新增(秒杀活动表)
	 * @param flashDealActivityList
	 * @return
	 */
	public int insertFlashDealActivityBatch(List<FlashDealActivity> flashDealActivityList);
	/**
	 * 更新(秒杀活动表)信息
	 * @param flashDealActivity
	 * @return
	 */
	public int updateFlashDealActivity(FlashDealActivity flashDealActivity);
	/**
	 * 批量更新(秒杀活动表)信息
	 * @param flashDealActivityList
	 * @return
	 */
	public int updateFlashDealActivityBatch(List<FlashDealActivity> flashDealActivityList);
	/**
	 * 根据序列号删除(秒杀活动表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteFlashDealActivityLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(秒杀活动表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteFlashDealActivityLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(秒杀活动表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteFlashDealActivity(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(秒杀活动表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteFlashDealActivityBatch(List<java.math.BigInteger> idList);
	
}
