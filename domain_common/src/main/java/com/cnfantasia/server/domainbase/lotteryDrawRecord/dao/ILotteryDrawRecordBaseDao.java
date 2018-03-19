package com.cnfantasia.server.domainbase.lotteryDrawRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.lotteryDrawRecord.entity.LotteryDrawRecord;

/**
 * 描述:(幸运抽奖记录表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILotteryDrawRecordBaseDao {
	/**
	 * 根据条件查询(幸运抽奖记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<LotteryDrawRecord> selectLotteryDrawRecordByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(幸运抽奖记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<LotteryDrawRecord> selectLotteryDrawRecordByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(幸运抽奖记录表)信息
	 * @param id
	 * @return
	 */
	LotteryDrawRecord selectLotteryDrawRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(幸运抽奖记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectLotteryDrawRecordCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(幸运抽奖记录表)新增一条记录
	 * @param lotteryDrawRecord
	 * @return
	 */
	int insertLotteryDrawRecord(LotteryDrawRecord lotteryDrawRecord);
	
	/**
	 * 批量新增(幸运抽奖记录表)信息
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
	 * 根据Id 批量删除(幸运抽奖记录表)信息_逻辑删除
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
