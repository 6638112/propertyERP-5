package com.cnfantasia.server.domainbase.prizeRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;

/**
 * 描述:(中奖记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrizeRecordBaseDao {
	/**
	 * 根据条件查询(中奖记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeRecord> selectPrizeRecordByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(中奖记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeRecord> selectPrizeRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(中奖记录)信息
	 * @param id
	 * @return
	 */
	public PrizeRecord selectPrizeRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(中奖记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPrizeRecordCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(中奖记录)新增一条记录
	 * @param prizeRecord
	 * @return
	 */
	public int insertPrizeRecord(PrizeRecord prizeRecord);
	
	/**
	 * 批量新增(中奖记录)信息
	 * @param prizeRecordList
	 * @return
	 */
	public int insertPrizeRecordBatch(List<PrizeRecord> prizeRecordList);
	
	/**
	 * 更新(中奖记录)信息
	 * @param prizeRecord
	 * @return
	 */
	public int updatePrizeRecord(PrizeRecord prizeRecord);
	
	/**
	 * 批量更新(中奖记录)信息
	 * @param prizeRecordList
	 * @return
	 */
	public int updatePrizeRecordBatch(List<PrizeRecord> prizeRecordList);
	
	/**
	 * 根据序列号删除(中奖记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePrizeRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(中奖记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePrizeRecordLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(中奖记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePrizeRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(中奖记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePrizeRecordBatch(List<java.math.BigInteger> idList);
	
	
}
