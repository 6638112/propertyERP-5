package com.cnfantasia.server.domainbase.flashDealBuyRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.flashDealBuyRecord.entity.FlashDealBuyRecord;

/**
 * 描述:(秒杀活动购买记录表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IFlashDealBuyRecordBaseService {
	
	/**
	 * 根据条件查询(秒杀活动购买记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<FlashDealBuyRecord> getFlashDealBuyRecordByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(秒杀活动购买记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<FlashDealBuyRecord> getFlashDealBuyRecordByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(秒杀活动购买记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<FlashDealBuyRecord> getFlashDealBuyRecordByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(秒杀活动购买记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<FlashDealBuyRecord> getFlashDealBuyRecordByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(秒杀活动购买记录表)信息
	 * @param id
	 * @return
	 */
	public FlashDealBuyRecord getFlashDealBuyRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(秒杀活动购买记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getFlashDealBuyRecordCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(秒杀活动购买记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getFlashDealBuyRecordCountDim(Map<String, Object> paramMap);
	/**
	 * 往(秒杀活动购买记录表)新增一条记录
	 * @param flashDealBuyRecord
	 * @return
	 */
	public int insertFlashDealBuyRecord(FlashDealBuyRecord flashDealBuyRecord);
	/**
	 * 批量新增(秒杀活动购买记录表)
	 * @param flashDealBuyRecordList
	 * @return
	 */
	public int insertFlashDealBuyRecordBatch(List<FlashDealBuyRecord> flashDealBuyRecordList);
	/**
	 * 更新(秒杀活动购买记录表)信息
	 * @param flashDealBuyRecord
	 * @return
	 */
	public int updateFlashDealBuyRecord(FlashDealBuyRecord flashDealBuyRecord);
	/**
	 * 批量更新(秒杀活动购买记录表)信息
	 * @param flashDealBuyRecordList
	 * @return
	 */
	public int updateFlashDealBuyRecordBatch(List<FlashDealBuyRecord> flashDealBuyRecordList);
	/**
	 * 根据序列号删除(秒杀活动购买记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteFlashDealBuyRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(秒杀活动购买记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteFlashDealBuyRecordLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(秒杀活动购买记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteFlashDealBuyRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(秒杀活动购买记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteFlashDealBuyRecordBatch(List<java.math.BigInteger> idList);
	
}
