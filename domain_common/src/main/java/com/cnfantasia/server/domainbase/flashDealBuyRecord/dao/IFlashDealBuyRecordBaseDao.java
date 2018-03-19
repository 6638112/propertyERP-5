package com.cnfantasia.server.domainbase.flashDealBuyRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.flashDealBuyRecord.entity.FlashDealBuyRecord;

/**
 * 描述:(秒杀活动购买记录表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IFlashDealBuyRecordBaseDao {
	/**
	 * 根据条件查询(秒杀活动购买记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<FlashDealBuyRecord> selectFlashDealBuyRecordByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(秒杀活动购买记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<FlashDealBuyRecord> selectFlashDealBuyRecordByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(秒杀活动购买记录表)信息
	 * @param id
	 * @return
	 */
	public FlashDealBuyRecord selectFlashDealBuyRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(秒杀活动购买记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectFlashDealBuyRecordCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(秒杀活动购买记录表)新增一条记录
	 * @param flashDealBuyRecord
	 * @return
	 */
	public int insertFlashDealBuyRecord(FlashDealBuyRecord flashDealBuyRecord);
	
	/**
	 * 批量新增(秒杀活动购买记录表)信息
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
	 * 根据Id 批量删除(秒杀活动购买记录表)信息_逻辑删除
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
