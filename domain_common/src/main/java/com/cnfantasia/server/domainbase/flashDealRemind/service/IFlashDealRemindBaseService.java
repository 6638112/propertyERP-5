package com.cnfantasia.server.domainbase.flashDealRemind.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.flashDealRemind.entity.FlashDealRemind;

/**
 * 描述:(一元Go提醒) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IFlashDealRemindBaseService {
	
	/**
	 * 根据条件查询(一元Go提醒)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<FlashDealRemind> getFlashDealRemindByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(一元Go提醒)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<FlashDealRemind> getFlashDealRemindByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(一元Go提醒)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<FlashDealRemind> getFlashDealRemindByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(一元Go提醒)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<FlashDealRemind> getFlashDealRemindByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(一元Go提醒)信息
	 * @param id
	 * @return
	 */
	FlashDealRemind getFlashDealRemindBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(一元Go提醒)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getFlashDealRemindCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(一元Go提醒)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getFlashDealRemindCountDim(Map<String, Object> paramMap);
	/**
	 * 往(一元Go提醒)新增一条记录
	 * @param flashDealRemind
	 * @return
	 */
	int insertFlashDealRemind(FlashDealRemind flashDealRemind);
	/**
	 * 批量新增(一元Go提醒)
	 * @param flashDealRemindList
	 * @return
	 */
	int insertFlashDealRemindBatch(List<FlashDealRemind> flashDealRemindList);
	/**
	 * 更新(一元Go提醒)信息
	 * @param flashDealRemind
	 * @return
	 */
	int updateFlashDealRemind(FlashDealRemind flashDealRemind);
	/**
	 * 批量更新(一元Go提醒)信息
	 * @param flashDealRemindList
	 * @return
	 */
	int updateFlashDealRemindBatch(List<FlashDealRemind> flashDealRemindList);
	/**
	 * 根据序列号删除(一元Go提醒)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteFlashDealRemindLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(一元Go提醒)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteFlashDealRemindLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(一元Go提醒)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteFlashDealRemind(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(一元Go提醒)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteFlashDealRemindBatch(List<java.math.BigInteger> idList);
	
}
