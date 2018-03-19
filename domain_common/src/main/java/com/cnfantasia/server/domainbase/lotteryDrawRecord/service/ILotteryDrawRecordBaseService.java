package com.cnfantasia.server.domainbase.lotteryDrawRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.lotteryDrawRecord.entity.LotteryDrawRecord;

/**
 * 描述:(幸运抽奖记录表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILotteryDrawRecordBaseService {
	
	/**
	 * 根据条件查询(幸运抽奖记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<LotteryDrawRecord> getLotteryDrawRecordByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(幸运抽奖记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<LotteryDrawRecord> getLotteryDrawRecordByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(幸运抽奖记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<LotteryDrawRecord> getLotteryDrawRecordByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(幸运抽奖记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<LotteryDrawRecord> getLotteryDrawRecordByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(幸运抽奖记录表)信息
	 * @param id
	 * @return
	 */
	LotteryDrawRecord getLotteryDrawRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(幸运抽奖记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getLotteryDrawRecordCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(幸运抽奖记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getLotteryDrawRecordCountDim(Map<String, Object> paramMap);
	/**
	 * 往(幸运抽奖记录表)新增一条记录
	 * @param lotteryDrawRecord
	 * @return
	 */
	int insertLotteryDrawRecord(LotteryDrawRecord lotteryDrawRecord);
	/**
	 * 批量新增(幸运抽奖记录表)
	 * @param lotteryDrawRecordList
	 * @return
	 */
	int insertLotteryDrawRecordBatch(List<LotteryDrawRecord> lotteryDrawRecordList);
	/**
	 * 更新(幸运抽奖记录表)信息
	 * @param lotteryDrawRecord
	 * @return
	 */
	int updateLotteryDrawRecord(LotteryDrawRecord lotteryDrawRecord);
	/**
	 * 批量更新(幸运抽奖记录表)信息
	 * @param lotteryDrawRecordList
	 * @return
	 */
	int updateLotteryDrawRecordBatch(List<LotteryDrawRecord> lotteryDrawRecordList);
	/**
	 * 根据序列号删除(幸运抽奖记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteLotteryDrawRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(幸运抽奖记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteLotteryDrawRecordLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(幸运抽奖记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLotteryDrawRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(幸运抽奖记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLotteryDrawRecordBatch(List<java.math.BigInteger> idList);
	
}
